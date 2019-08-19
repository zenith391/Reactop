package io.zenith391.reactop.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class NeutronInjector extends Block {

	public NeutronInjector() {
		super(FabricBlockSettings.of(Material.METAL)
				.lightLevel(5)
				.strength(3f, 2f)
				.build());
	}

}
