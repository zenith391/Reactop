package io.zenith391.reactop.block.be;

import java.util.HashMap;
import java.util.Map;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.gui.HeatFurnaceDescription;
import io.zenith391.reactop.registry.BlockRegistry;
import io.zenith391.reactop.util.InventoryImplementation;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class HeatFurnaceBlockEntity extends AbstractHeatBlockEntity implements NamedScreenHandlerFactory, InventoryImplementation {

	private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
	private int burnTime = 0;
	private Item fuel = null;
	
	@Override
	public DefaultedList<ItemStack> getItems() {
		return items;
	}
	
	public HeatFurnaceBlockEntity() {
		super(BlockRegistry.HEAT_FURNACE_ENTITY);
		isolation = 0.98d;
	}
	
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		Inventories.toTag(tag, items);
		tag.putInt("burnTime", burnTime);
		tag.putBoolean("hasFuel", fuel != null);
		if (fuel != null) {
			tag.putString("fuel", Registry.ITEM.getKey(fuel).get().getValue().toString());
		}
		return tag;
	}
	
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		Inventories.fromTag(tag, items);
		burnTime = tag.getInt("burnTime");
		if (tag.getBoolean("hasFuel")) {
			fuel = Registry.ITEM.get(Identifier.tryParse(tag.getString("fuel")));
		}
	}
	
	private static final Map<Item, Integer> FUEL_HEAT_MAP = createFuelHeatMap();
	private static final Map<Item, Integer> FUEL_TIME_MAP = AbstractFurnaceBlockEntity.createFuelTimeMap();
	
	private static Map<Item, Integer> createFuelHeatMap() {
		HashMap<Item, Integer> map = new HashMap<Item, Integer>();
		map.put(Items.COAL, 1300);
		map.put(Items.CHARCOAL, 900);
		return map;
	}

	@Override
	public void tick() {
		super.tick();
		
		if (burnTime == 0) {
			ItemStack fuel = getStack(0);
			if (!fuel.isEmpty()) {
				Item item = fuel.getItem();
				if (FUEL_TIME_MAP.containsKey(item)) {
					burnTime = FUEL_TIME_MAP.get(item);
					fuel.decrement(1);
					this.fuel = item;
				}
			}
		} else {
			burnTime--;
			isolation = 0.995d;
			double t = 0.9;
			int targetHeat = FUEL_HEAT_MAP.getOrDefault(fuel, 800);
			HeatComponent heat = getHeatComponent();
			if (heat.getHeat() < targetHeat) {
				heat.setHeat(heat.getHeat() * t + targetHeat * (1 - t));
			}
		}
	}

	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
		return new HeatFurnaceDescription(syncId, inventory, ScreenHandlerContext.create(world, pos), this);
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText(getCachedState().getBlock().getTranslationKey());
	}
	
}
