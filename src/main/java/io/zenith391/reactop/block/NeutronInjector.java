package io.zenith391.reactop.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class NeutronInjector extends Block {

	public static final EnumProperty<Direction> DIRECTION = EnumProperty.of("direction", Direction.class);
	
	public NeutronInjector() {
		super(FabricBlockSettings.of(Material.METAL)
				.lightLevel(5)
				.strength(3f, 2f)
				.build());
		setDefaultState(getStateFactory().getDefaultState()
				.with(DIRECTION, Direction.UP)
			);
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(DIRECTION);
	}
	
	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		
	}

}
