package io.zenith391.reactop;

import io.zenith391.reactop.registry.BlockRegistry;
import io.zenith391.reactop.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ReactopMod implements ModInitializer {
	
	public static final ItemGroup REACTOP_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("reactop", "item_group"))
			.icon(() -> new ItemStack(ItemRegistry.MILIDIUM_INGOT, 1))
			.build();
	
	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
	}
	
}
