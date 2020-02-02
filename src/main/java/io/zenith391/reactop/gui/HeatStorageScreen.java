package io.zenith391.reactop.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class HeatStorageScreen extends CottonInventoryScreen<HeatStorageController> {

	public HeatStorageScreen(HeatStorageController container, PlayerEntity player) {
		super(container, player);
	}

}
