package io.zenith391.reactop.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;

public class NeutronInjector extends Block {

	public static final EnumProperty<Direction> DIRECTION = EnumProperty.of("direction", Direction.class);
	
	public NeutronInjector() {
		super(FabricBlockSettings.of(Material.METAL)
				.lightLevel(5)
				.strength(3f, 2f)
				.build());
		setDefaultState(getStateManager().getDefaultState()
				.with(DIRECTION, Direction.UP)
			);
	}
	
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction dir = ctx.getPlayerLookDirection().getOpposite();
		return getDefaultState().with(DIRECTION, dir);
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(DIRECTION);
	}

}
