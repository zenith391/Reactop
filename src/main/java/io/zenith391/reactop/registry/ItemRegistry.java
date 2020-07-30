package io.zenith391.reactop.registry;

import java.util.ArrayList;

import io.zenith391.reactop.ReactopMod;
import io.zenith391.reactop.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

	/**
	 * Blocks that need to have an ItemBlock
	 */
	public static final ArrayList<Block> BLOCKS;
	
	public static final Item MILIDIUM_INGOT;
	public static final Item USELESS_MILIDIUM_INGOT;
	public static final Item MILIDIUM_BAR;
	public static final Item TEMPERATURE_GAUGE;
	
	static {
		BLOCKS = new ArrayList<>();
		
		MILIDIUM_INGOT = new MilidiumIngot();
		USELESS_MILIDIUM_INGOT = new UselessMilidiumIngot();
		MILIDIUM_BAR = new MilidiumBar();
		TEMPERATURE_GAUGE = new TemperatureGauge();
	}
	
	public static Item.Settings generateSettings(Block block) {
		return new Item.Settings().group(ReactopMod.REACTOP_ITEM_GROUP);
	}
	
	static void register(String id, Item i) {
		Registry.register(Registry.ITEM, new Identifier("reactop", id), i);
	}
	
	public static void register() {
		for (Block block : BLOCKS) {
			BlockItem item = new BlockItem(block, generateSettings(block));
			Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), item);
		}
		
		register("milidium_ingot", MILIDIUM_INGOT);
		register("useless_milidium_ingot", USELESS_MILIDIUM_INGOT);
		register("milidium_bar", MILIDIUM_BAR);
		register("temperature_gauge", TEMPERATURE_GAUGE);
	}
	
}
