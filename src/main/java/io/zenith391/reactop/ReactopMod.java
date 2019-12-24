package io.zenith391.reactop;

import io.zenith391.reactop.block.HeatStorage;
import io.zenith391.reactop.gui.HeatStorageController;
import io.zenith391.reactop.registry.BlockRegistry;
import io.zenith391.reactop.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class ReactopMod implements ModInitializer {
	
	public static final ItemGroup REACTOP_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("reactop", "item_group"))
			.icon(() -> new ItemStack(Registry.ITEM.get(new Identifier("reactop", "neutron_injector")), 1))
			.build();
	
	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		ContainerProviderRegistry.INSTANCE.registerFactory(HeatStorage.GUI_ID,
			(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) -> {
				return new HeatStorageController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos()));
			});
	}
	
}
