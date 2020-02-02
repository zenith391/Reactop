package io.zenith391.reactop.gui;

import java.util.Optional;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WBar.Direction;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.zenith391.reactop.block.be.HeatStorageBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;

public class HeatStorageController extends CottonCraftingController {
	
	private HeatStorageBlockEntity be;
	private WBar bar;
	
	public HeatStorageController(int syncId, PlayerInventory playerInventory, BlockContext ctx) {
		super(RecipeType.SMELTING, syncId, playerInventory);
		
		WGridPanel root = (WGridPanel) getRootPanel();
		Optional<BlockEntity> optional = ctx.run((world, pos) -> {
			return world.getBlockEntity(pos);
		});
		
		if (optional.isPresent()) {
			if (optional.get() instanceof HeatStorageBlockEntity) {
				be = (HeatStorageBlockEntity) optional.get();
			}
		}
		
		bar = new WBar(null, null, 0, 1, Direction.RIGHT);
		
		root.add(new WLabel("Heat Storage"), 0, 0);
		root.add(bar, 0, 1);
		bar.setSize(200, 25);
		
		root.validate(this);
	}
	
	public PropertyDelegate getPropertyDelegate() {
		return new PropertyDelegate() {

			@Override
			public int get(int id) {
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
