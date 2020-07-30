package io.zenith391.reactop.client;

import io.zenith391.reactop.block.HeatStorage;
import io.zenith391.reactop.block.be.ReactorTankEntityRenderer;
import io.zenith391.reactop.gui.HeatStorageDescription;
import io.zenith391.reactop.gui.HeatStorageScreen;
import io.zenith391.reactop.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class ReactopClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ScreenRegistry.<HeatStorageDescription, HeatStorageScreen>register(HeatStorage.SCREEN_HANDLER_TYPE,
				new ScreenRegistry.Factory<HeatStorageDescription, HeatStorageScreen>() {
					@Override
					public HeatStorageScreen create(HeatStorageDescription handler, PlayerInventory inventory,
							Text title) {
						return new HeatStorageScreen(handler, inventory.player, title);
					}
				});
		BlockEntityRendererRegistry.INSTANCE.register(BlockRegistry.REACTOR_TANK_ENTITY, ReactorTankEntityRenderer::new);
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.REACTOR_TANK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.HEAT_CONDUCTOR, RenderLayer.getCutout());
	}

}
