package io.zenith391.reactop.block;

import io.zenith391.reactop.block.be.ReactorTankBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.world.BlockView;

public class ReactorTank extends Block implements BlockEntityProvider {

	public static final BooleanProperty UP = BooleanProperty.of("up");
	public static final BooleanProperty DOWN = BooleanProperty.of("down");
	
	public ReactorTank() {
		super(FabricBlockSettings.of(Material.METAL)
				.hardness(2.f)
				.resistance(1.f)
				.build()
				);
		setDefaultState(getStateFactory().getDefaultState()
				.with(UP, false)
				.with(DOWN, false));
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new ReactorTankBlockEntity();
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(UP);
		stateFactory.add(DOWN);
	}
	
}
