package io.zenith391.reactop.registry;

import io.zenith391.reactop.block.*;
import io.zenith391.reactop.block.be.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
	
	public static final Block MILIDIUM_BLOCK;
	public static final Block USELESS_MILIDIUM_BLOCK;
	public static final Block HEAT_CONDUCTOR;
	public static final Block HEAT_STORAGE;
	public static final Block NEUTRON_INJECTOR;
	public static final Block REACTOR_TANK;
	
	public static BlockEntityType<HeatConducterBlockEntity> HEAT_CONDUCTER_ENTITY;
	public static BlockEntityType<HeatStorageBlockEntity> HEAT_STORAGE_ENTITY;
	public static BlockEntityType<ReactorTankBlockEntity> REACTOR_TANK_ENTITY;
	public static BlockEntityType<NeutronInjectorBlockEntity> NEUTRON_INJECTOR_ENTITY;
	
	static {
		MILIDIUM_BLOCK = new MilidiumBlock();
		USELESS_MILIDIUM_BLOCK = new UselessMilidiumBlock();
		HEAT_CONDUCTOR = new HeatConductor();
		HEAT_STORAGE = new HeatStorage();
		NEUTRON_INJECTOR = new NeutronInjector();
		REACTOR_TANK = new ReactorTank();
	}
	
	static void register(String id, Block block) {
		Registry.BLOCK.add(new Identifier("reactop", id), block);
		ItemRegistry.BLOCKS.add(block);
	}
	
	public static void register() {
		register("milidium_block", MILIDIUM_BLOCK);
		register("useless_milidium_block", USELESS_MILIDIUM_BLOCK);
		register("heat_conductor", HEAT_CONDUCTOR);
		register("heat_storage", HEAT_STORAGE);
		register("neutron_injector", NEUTRON_INJECTOR);
		register("reactor_tank", REACTOR_TANK);
		
		// Block Entities
		HEAT_CONDUCTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "heat_conducter_entity"),
				BlockEntityType.Builder.create(HeatConducterBlockEntity::new, HEAT_CONDUCTOR).build(null));
		
		HEAT_STORAGE_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "heat_storage_entity"),
				BlockEntityType.Builder.create(HeatStorageBlockEntity::new, HEAT_STORAGE).build(null));
		
		REACTOR_TANK_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "reactor_tank_entity"),
				BlockEntityType.Builder.create(ReactorTankBlockEntity::new, REACTOR_TANK).build(null));
		NEUTRON_INJECTOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY,
				new Identifier("reactop", "neutron_injector_entity"),
				BlockEntityType.Builder.create(NeutronInjectorBlockEntity::new, NEUTRON_INJECTOR).build(null));
	}
}
