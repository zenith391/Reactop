package io.zenith391.reactop;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import io.zenith391.reactop.block.be.AbstractHeatBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class ReactopComponents implements BlockComponentInitializer {

	public static final ComponentKey<HeatComponent> HEAT =
			ComponentRegistry.getOrCreate(new Identifier("reactop", "heat"), HeatComponent.class);

	@Override
	public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
		registry.registerFor(AbstractHeatBlockEntity.class, HEAT, (be) -> {
			return new HeatComponentImpl(be, 1000, (target) -> {
				be.getWorld().setBlockState(be.getPos(), Blocks.LAVA.getDefaultState());
			}); // TODO: custom capacity
		});
	}
	
}
