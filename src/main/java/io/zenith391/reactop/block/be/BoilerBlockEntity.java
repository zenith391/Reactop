package io.zenith391.reactop.block.be;

import io.zenith391.reactop.gui.HeatStorageDescription;
import io.zenith391.reactop.registry.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class BoilerBlockEntity extends AbstractHeatBlockEntity implements NamedScreenHandlerFactory {
	
	public BoilerBlockEntity() {
		super(BlockRegistry.BOILER_ENTITY);
	}

	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
		return new HeatStorageDescription(syncId, inventory, ScreenHandlerContext.create(world, pos), this);
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText(getCachedState().getBlock().getTranslationKey());
	}
	
}
