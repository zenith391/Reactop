package io.zenith391.reactop.registry;

import io.zenith391.reactop.block.*;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
	
	public static final Block URANIUM235_BLOCK;
	public static final Block URANIUM238_BLOCK;
	
	static {
		URANIUM235_BLOCK = new MilidiumBlock();
		URANIUM238_BLOCK = new UselessMilidiumBlock();
	}
	
	static void register(String id, Block block) {
		Registry.BLOCK.add(new Identifier("reactop", id), block);
		ItemRegistry.BLOCKS.add(block);
	}
	
	public static void register() {
		register("milidium_block", URANIUM235_BLOCK);
		register("useless_milidium_block", URANIUM238_BLOCK);
	}
	
}
