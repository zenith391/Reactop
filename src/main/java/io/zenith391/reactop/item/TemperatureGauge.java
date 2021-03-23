package io.zenith391.reactop.item;

import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.ReactopComponents;
import io.zenith391.reactop.ReactopMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;

public class TemperatureGauge extends Item {

	public TemperatureGauge() {
		super(new Item.Settings()
				.group(ReactopMod.REACTOP_ITEM_GROUP));
	}
	
	@SuppressWarnings("resource")
	public ActionResult useOnBlock(ItemUsageContext usage) {
		if (!usage.getWorld().isClient) return ActionResult.FAIL;
		HeatComponent heat = BlockComponents.get(ReactopComponents.HEAT, usage.getWorld(), usage.getBlockPos());
		if (heat != null) {
			usage.getPlayer().sendMessage(new LiteralText("Heat: " + (int) heat.getHeat() + "/" + (int) heat.getCapacity() + "°C"), true);
			return ActionResult.SUCCESS;
		}
		return ActionResult.FAIL;
	}

}
