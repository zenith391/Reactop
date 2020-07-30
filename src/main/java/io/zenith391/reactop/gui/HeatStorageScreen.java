package io.zenith391.reactop.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class HeatStorageScreen extends CottonInventoryScreen<HeatStorageDescription> {

	public HeatStorageScreen(HeatStorageDescription gui, PlayerEntity player, Text title) {
		super(gui, player, title);
	}

}
