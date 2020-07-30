package io.zenith391.reactop;

import java.util.function.Consumer;

import net.minecraft.nbt.CompoundTag;

public class HeatComponentImpl implements HeatComponent {

	protected double heat;
	protected double capacity = 1000;
	protected Consumer<Double> meltdownHandler;
	
	@Override
	public double getHeat() {
		return heat;
	}

	@Override
	public void setHeat(double heat) {
		this.heat = heat;
	}

	@Override
	public double getCapacity() {
		return capacity;
	}

	@Override
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	public boolean canGetHeat(double currValue) {
		return currValue < heat;
	}

	@Override
	public double addHeat(double heat) {
		if (this.heat + heat <= capacity) {
			this.heat += heat;
			return heat;
		} else {
			if (meltdownHandler != null) {
				meltdownHandler.accept(this.heat + heat);
			}
			return heat;
		}
	}

	@Override
	public void setMeltdownHandler(Consumer<Double> consumer) {
		meltdownHandler = consumer;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		if (tag.contains("heat"))
			heat = tag.getDouble("heat");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.putDouble("heat", heat);
		return tag;
	}

}
