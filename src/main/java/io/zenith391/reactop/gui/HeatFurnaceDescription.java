package io.zenith391.reactop.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WBar.Direction;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.zenith391.reactop.block.HeatFurnace;
import io.zenith391.reactop.block.be.HeatFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;

public class HeatFurnaceDescription extends SyncedGuiDescription {
	
	public HeatFurnaceDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext ctx, HeatFurnaceBlockEntity be) {
		super(HeatFurnace.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(ctx, 1), getBlockPropertyDelegate(ctx));
		
		WGridPanel root = new WGridPanel();
		setRootPanel(root);
		root.setSize(300, 200);
		
		WItemSlot slot = WItemSlot.of(blockInventory, 0);
		root.add(slot, 4, 1);
		root.add(this.createPlayerInventoryPanel(), 0, 3);
		
		root.validate(this);
	}

}
