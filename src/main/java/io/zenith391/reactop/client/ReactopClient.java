package io.zenith391.reactop.client;

import io.zenith391.reactop.block.HeatStorage;
import io.zenith391.reactop.block.be.ReactorTankEntityRenderer;
import io.zenith391.reactop.gui.HeatStorageController;
import io.zenith391.reactop.gui.HeatStorageScreen;
import io.zenith391.reactop.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ReactopClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ScreenProviderRegistry.INSTANCE.registerFactory(HeatStorage.GUI_ID, 
			(int syncId, Identifier identifier, PlayerEntity player,PacketByteBuf buf) -> {
				return new HeatStorageScreen(new HeatStorageController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player);
			});
		BlockEntityRendererRegistry.INSTANCE.register(BlockRegistry.REACTOR_TANK_ENTITY, ReactorTankEntityRenderer::new);
	}

}
