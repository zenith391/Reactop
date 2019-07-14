package io.zenith391.reactop.item;

import io.zenith391.reactop.ReactopMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class MilidiumIngot extends Item {

	public MilidiumIngot() {
		super(new Item.Settings()
				.group(ReactopMod.REACTOP_ITEM_GROUP));
	}
	
	public Text getName(ItemStack stack) {
		TranslatableText normal = new TranslatableText("item.reactop.milidium_ingot");
		TranslatableText unknown = new TranslatableText("custom.reactop.unknown_milidium_type");
		if (stack.getTag() == null) {
			CompoundTag tag = new CompoundTag();
			tag.putBoolean("discovered", false);
			stack.setTag(tag);
			return unknown;
		} else {
			if (stack.getTag().getBoolean("discovered")) {
				return normal;
			} else {
				return unknown;
			}
		}
	}

}
