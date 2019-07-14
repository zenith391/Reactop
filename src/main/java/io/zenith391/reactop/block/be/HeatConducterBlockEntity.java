package io.zenith391.reactop.block.be;

import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.registry.BlockRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class HeatConducterBlockEntity extends BlockEntity {

	// Heat is in Celcius, 0=20°C,-20=0°C,20=40°C
	HeatComponent heat = new HeatComponent();
	
	public HeatConducterBlockEntity() {
		super(BlockRegistry.HEAT_CONDUCTER_ENTITY);
	}
	
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.putDouble("heat", heat.getHeat());
		return tag;
	}
	
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		heat.setHeat(tag.getDouble("heat"));
	}

	public HeatComponent getHeatComponent() {
		return heat;
	}
	
}
