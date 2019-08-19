package io.zenith391.reactop.block;

import java.util.Set;

import com.google.common.collect.Sets;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.block.be.HeatConducterBlockEntity;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class HeatConducter extends Block implements BlockComponentProvider, BlockEntityProvider  {

	public static final BooleanProperty DOWN = BooleanProperty.of("down");
	public static final BooleanProperty UP = BooleanProperty.of("up");
	public static final BooleanProperty WEST = BooleanProperty.of("west");
	public static final BooleanProperty EAST = BooleanProperty.of("east");
	public static final BooleanProperty NORTH = BooleanProperty.of("north");
	public static final BooleanProperty SOUTH = BooleanProperty.of("south");
	
	private static final Material MATERIAL = new Material(
			MaterialColor.AIR,
			false,
			false,
			false,
			true,
			true,
			false,
			false,
			PistonBehavior.NORMAL
		);
	
	public HeatConducter() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2.f)
				.build()
				);
		setDefaultState(getStateFactory().getDefaultState()
				.with(DOWN, false)
				.with(UP, false)
				.with(WEST, false)
				.with(EAST, false)
				.with(NORTH, false)
				.with(SOUTH, false));
	}
	
	private boolean canConnectTo(Block block, BlockPos pos, World world) {
		if (block instanceof BlockComponentProvider) {
			BlockComponentProvider provider = (BlockComponentProvider) block;
			if (provider.hasComponent(world, pos, ComponentTypes.HEAT_COMPONENT, null)) {
				return true;
			}
		}
		return false;
	}
	
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block oldBlock, BlockPos pos2, boolean bool) {
		super.neighborUpdate(state, world, pos, oldBlock, pos2, bool);
		Block block = world.getBlockState(pos2).getBlock();
		boolean destroyed = Blocks.AIR == block;
		
		if (canConnectTo(block, pos, world) || block == Blocks.AIR) {
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
	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(UP);
		stateFactory.add(DOWN);
		stateFactory.add(WEST);
		stateFactory.add(EAST);
		stateFactory.add(NORTH);
		stateFactory.add(SOUTH);
	}

	@Override
	public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		return type == ComponentTypes.HEAT_COMPONENT;
	}

	@Override
	public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		if (type == ComponentTypes.HEAT_COMPONENT) {
			return (T) ((HeatConducterBlockEntity) blockView.getBlockEntity(pos)).getHeatComponent();
		}
		return null;
	}

	@Override
	public Set<ComponentType<? extends Component>> getComponentTypes(BlockView blockView, BlockPos pos,
			Direction side) {
		return Sets.newHashSet(ComponentTypes.HEAT_COMPONENT);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new HeatConducterBlockEntity();
	}
	
}
