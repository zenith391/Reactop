package io.zenith391.reactop.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WBar.Direction;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.zenith391.reactop.block.Boiler;
import io.zenith391.reactop.block.be.BoilerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;

public class HeatStorageDescription extends SyncedGuiDescription {
	
	private BoilerBlockEntity be;
	private WBar bar;
	
	public HeatStorageDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext ctx, BoilerBlockEntity be) {
		super(Boiler.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(ctx, 0), getBlockPropertyDelegate(ctx));
		
		WGridPanel root = new WGridPanel();
		setRootPanel(root);
		this.be = be;
		
		bar = new WBar((Texture) null, (Texture) null, 0, 1, Direction.RIGHT);
		
		root.add(bar, 0, 1);
		bar.setSize(200, 25);
		
		root.validate(this);
	}
	
	public PropertyDelegate getPropertyDelegate() {
		return new PropertyDelegate() {

			@Override
			public int get(int id) {
				if (be == null) return 0;
				switch(id) {
				case 0:
					return (int) be.getHeatComponent().getHeat();
				case 1:
					return (int) be.getHeatComponent().getCapacity();
				default:
					return 0;
				}
			}

			@Override
			public void set(int id, int value) {}

			@Override
			public int size() {
				return 2;
			}
			
		};
	}

}
