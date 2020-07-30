package io.zenith391.reactop.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class MilidiumBlock extends Block {

	public MilidiumBlock() {
		super(FabricBlockSettings.of(Material.METAL)
				.hardness(2.f)
				.resistance(1.f)
				);
	}

}
