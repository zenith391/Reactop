package io.zenith391.reactop.registry;

import io.zenith391.reactop.block.*;
import io.zenith391.reactop.block.be.HeatConducterBlockEntity;
import io.zenith391.reactop.block.be.HeatStorageBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
	
	public static final Block MILIDIUM_BLOCK;
	public static final Block USELESS_MILIDIUM_BLOCK;
	public static final Block HEAT_CONDUCTER;
	public static final Block HEAT_STORAGE;
	public static final Block NEUTRON_INJECTOR;
	
	public static BlockEntityType<HeatConducterBlockEntity> HEAT_CONDUCTER_ENTITY;
	public static BlockEntityType<HeatStorageBlockEntity> HEAT_STORAGE_ENTITY;
	
	static {
		MILIDIUM_BLOCK = new MilidiumBlock();
		USELESS_MILIDIUM_BLOCK = new UselessMilidiumBlock();
		HEAT_CONDUCTER = new HeatConducter();
		HEAT_STORAGE = new HeatStorage();
		NEUTRON_INJECTOR = new NeutronInjector();
	}
	
	static void register(String id, Block block) {
		Registry.BLOCK.add(new Identifier("reactop", id), block);
		ItemRegistry.BLOCKS.add(block);
	}
	
	public static void register() {
		register("milidium_block", MILIDIUM_BLOCK);
		register("useless_milidium_block", USELESS_MILIDIUM_BLOCK);
		register("heat_conducter", HEAT_CONDUCTER);
		register("heat_storage", HEAT_STORAGE);
		register("neutron_injector", NEUTRON_INJECTOR);
		
		// Block Entities
		HEAT_CONDUCTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "heat_conducter_entity"),
				BlockEntityType.Builder.create(HeatConducterBlockEntity::new, HEAT_CONDUCTER).build(null));
		
		HEAT_STORAGE_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "heat_storage__entity"),
				BlockEntityType.Builder.create(HeatStorageBlockEntity::new, HEAT_STORAGE).build(null));
	}
	
}
