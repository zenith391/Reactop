package io.zenith391.reactop.item;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.ReactopMod;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;

public class TemperatureGauge extends Item {

	public TemperatureGauge() {
		super(new Item.Settings()
				.group(ReactopMod.REACTOP_ITEM_GROUP));
	}
	
	public ActionResult useOnBlock(ItemUsageContext usage) {
		if (!usage.getWorld().isClient) return ActionResult.FAIL;
		Block block = usage.getWorld().getBlockState(usage.getBlockPos()).getBlock();
		if (block instanceof BlockComponentProvider) {
			BlockComponentProvider provider = (BlockComponentProvider) block;
			if (provider.hasComponent(usage.getWorld(), usage.getBlockPos(), ComponentTypes.HEAT_COMPONENT, null)) {
				HeatComponent hc = provider.getComponent(usage.getWorld(), usage.getBlockPos(), ComponentTypes.HEAT_COMPONENT, null);
				usage.getPlayer().sendMessage(new LiteralText("Heat: " + (int) hc.getHeat() + "/" + (int) hc.getCapacity() + "°C"));
				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.FAIL;
	}

}
