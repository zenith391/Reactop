package io.zenith391.reactop.block;

import java.util.HashSet;
import java.util.Set;

import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.ReactopComponents;
import io.zenith391.reactop.block.be.HeatConducterBlockEntity;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class HeatConductor extends Block implements BlockEntityProvider  {

	public static final BooleanProperty DOWN = BooleanProperty.of("down");
	public static final BooleanProperty UP = BooleanProperty.of("up");
	public static final BooleanProperty WEST = BooleanProperty.of("west");
	public static final BooleanProperty EAST = BooleanProperty.of("east");
	public static final BooleanProperty NORTH = BooleanProperty.of("north");
	public static final BooleanProperty SOUTH = BooleanProperty.of("south");
	
	private static final Material MATERIAL = new Material(
			MaterialColor.BLACK,
			false,
			false,
			false,
			false,
			true,
			false,
			PistonBehavior.NORMAL
		);
	
	public HeatConductor() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2f)
				.nonOpaque()
				.dynamicBounds()
				);
		setDefaultState(getStateManager().getDefaultState()
				.with(DOWN, false)
				.with(UP, false)
				.with(WEST, false)
				.with(EAST, false)
				.with(NORTH, false)
				.with(SOUTH, false));
	}
	
	private boolean canConnectTo(Block block, BlockPos pos, World world) {
		return BlockComponents.get(ReactopComponents.HEAT, world, pos) != null;
	}
	
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
		float bit = 1/16f;
		VoxelShape shape = VoxelShapes.cuboid(bit*4, bit*4, bit*4, bit*12, bit*12, bit*12);
		if (state.get(NORTH)) {
			VoxelShape shape2 = VoxelShapes.cuboid(bit*4, bit*4, 0, bit*12, bit*12, bit*5);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		if (state.get(SOUTH)) {
			VoxelShape shape2 = VoxelShapes.cuboid(bit*4, bit*4, bit*16, bit*12, bit*12, bit*12);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		if (state.get(EAST)) {
			VoxelShape shape2 = VoxelShapes.cuboid(bit*12, bit*4, bit*12, bit*16, bit*12, bit*4);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		if (state.get(WEST)) {
			VoxelShape shape2 = VoxelShapes.cuboid(0, bit*4, bit*12, bit*4, bit*12, bit*4);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		if (state.get(UP)) {
			VoxelShape shape2 = VoxelShapes.cuboid(bit*4, bit*12, bit*12, bit*12, bit*16, bit*4);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		if (state.get(DOWN)) {
			VoxelShape shape2 = VoxelShapes.cuboid(bit*4, 0, bit*12, bit*12, bit*4, bit*4);
			shape = VoxelShapes.combine(shape, shape2, (b1, b2) -> b1 | b2);
		}
		return shape;
	}
	
	public boolean isTranslucent(BlockState state, BlockView view, BlockPos pos) {
		return true;
	}
	
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		// Check all neighbours when placed
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1), false);
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block oldBlock, BlockPos pos2, boolean bool) {
		super.neighborUpdate(state, world, pos, oldBlock, pos2, bool);
		Block block = world.getBlockState(pos2).getBlock();
		boolean destroyed = Blocks.AIR == block;
		if (canConnectTo(block, pos2, world) || block == Blocks.AIR) {
			BooleanProperty prop = null;
			if (pos2.getX() > pos.getX()) {
				prop = EAST;
			}
			if (pos2.getX() < pos.getX()) {
				prop = WEST;
			}
			if (pos2.getY() > pos.getY()) {
				prop = UP;
			}
			if (pos2.getY() < pos.getY()) {
				prop = DOWN;
			}
			if (pos2.getZ() > pos.getZ()) {
				prop = SOUTH;
			}
			if (pos2.getZ() < pos.getZ()) {
				prop = NORTH;
			}
			
			if (destroyed) {
				world.setBlockState(pos, state.with(prop, false));
			} else {
				world.setBlockState(pos, state.with(prop, true));
			}
		}
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(UP);
		stateFactory.add(DOWN);
		stateFactory.add(WEST);
		stateFactory.add(EAST);
		stateFactory.add(NORTH);
		stateFactory.add(SOUTH);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new HeatConducterBlockEntity();
	}
	
}
