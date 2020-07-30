package io.zenith391.reactop.block;

import io.zenith391.reactop.block.be.ReactorTankBlockEntity;
import io.zenith391.reactop.registry.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ReactorTank extends Block implements BlockEntityProvider {

	public static final BooleanProperty UP = BooleanProperty.of("up");
	public static final BooleanProperty DOWN = BooleanProperty.of("down");
	
	public ReactorTank() {
		super(FabricBlockSettings.of(Material.METAL)
				.hardness(2.f)
				.resistance(1.f)
				.nonOpaque()
				);
		setDefaultState(getStateManager().getDefaultState()
				.with(UP, false)
				.with(DOWN, false));
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new ReactorTankBlockEntity();
	}
	
	public boolean isTranslucent(BlockState state, BlockView view, BlockPos pos) {
		return true;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(UP);
		stateFactory.add(DOWN);
	}
	
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		// Check all neighbours (that can affect this block) when placed
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), false);
		state = world.getBlockState(pos);
		neighborUpdate(state, world, pos, null, new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()), false);
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block oldBlock, BlockPos pos2, boolean bool) {
		super.neighborUpdate(state, world, pos, oldBlock, pos2, bool);
		Block block = world.getBlockState(pos2).getBlock();
		boolean destroyed = Blocks.AIR == block;
		if (block == BlockRegistry.REACTOR_TANK || block == Blocks.AIR) {
			BooleanProperty prop = null;
			if (pos2.getY() > pos.getY()) {
				prop = UP;
			}
			if (pos2.getY() < pos.getY()) {
				prop = DOWN;
			}
			
			if (prop != null) {
				if (destroyed) {
					world.setBlockState(pos, state.with(prop, false));
				} else {
					world.setBlockState(pos, state.with(prop, true));
				}
			}
		}
	}
	
}
