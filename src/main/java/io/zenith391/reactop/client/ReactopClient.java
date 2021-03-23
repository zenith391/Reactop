package io.zenith391.reactop.client;

import io.zenith391.reactop.block.Boiler;
import io.zenith391.reactop.block.HeatFurnace;
import io.zenith391.reactop.block.be.ReactorTankEntityRenderer;
import io.zenith391.reactop.gui.HeatFurnaceDescription;
import io.zenith391.reactop.gui.HeatFurnaceScreen;
import io.zenith391.reactop.gui.HeatStorageDescription;
import io.zenith391.reactop.gui.HeatStorageScreen;
import io.zenith391.reactop.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

public class ReactopClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ScreenRegistry.<HeatStorageDescription, HeatStorageScreen>register(Boiler.SCREEN_HANDLER_TYPE,
				(gui, inventory, title) -> new HeatStorageScreen(gui, inventory.player, title));
		
		ScreenRegistry.<HeatFurnaceDescription, HeatFurnaceScreen>register(HeatFurnace.SCREEN_HANDLER_TYPE,
				(gui, inventory, title) -> new HeatFurnaceScreen(gui, inventory.player, title));
		
		BlockEntityRendererRegistry.INSTANCE.register(BlockRegistry.REACTOR_TANK_ENTITY, ReactorTankEntityRenderer::new);
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.REACTOR_TANK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.HEAT_CONDUCTOR, RenderLayer.getCutout());
	}

}
