package io.zenith391.reactop.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class HeatFurnaceScreen extends CottonInventoryScreen<HeatFurnaceDescription> {

	public HeatFurnaceScreen(HeatFurnaceDescription gui, PlayerEntity player, Text title) {
		super(gui, player, title);
	}

}
