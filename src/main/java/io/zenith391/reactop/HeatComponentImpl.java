package io.zenith391.reactop;

import java.util.function.Consumer;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;

public class HeatComponentImpl implements HeatComponent, AutoSyncedComponent {

	protected double heat;
	/**
	 * The heat at the last ticking.
	 */
	protected double oldHeat;
	protected final double capacity;
	protected double conductivity = 0.5d;
	
	protected Consumer<Double> meltdownHandler;
	protected final Object provider;
	
	public HeatComponentImpl(Object provider, double capacity, Consumer<Double> meltdownHandler) {
		this.capacity = capacity;
		this.meltdownHandler = meltdownHandler;
		this.provider = provider;
	}
	
	public HeatComponentImpl(Object provider, double capacity) {
		this(provider, capacity, null);
	}
	
	@Override
	public double getHeat() {
		return heat;
	}

	@Override
	public void setHeat(double heat) {
		this.heat = heat;
		if (heat > capacity) {
			if (meltdownHandler != null) {
				meltdownHandler.accept(heat);
			}
		}
	}

	@Override
	public double getCapacity() {
		return capacity;
	}
	
	@Override
	public double getConductivity() {
		return conductivity;
	}

	@Override
	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}
	
	public boolean canGetHeat(double currValue) {
		return currValue < heat;
	}

	@Override
	public double addHeat(double heat) {
		setHeat(this.heat + heat);
		return heat;
	}

	@Override
	public void setMeltdownHandler(Consumer<Double> consumer) {
		meltdownHandler = consumer;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains("heat"))
			heat = tag.getDouble("heat");
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putDouble("heat", heat);
	}

	@Override
	public void serverTick() {
		if (oldHeat != heat) {
			ReactopComponents.HEAT.sync(this.provider);
			oldHeat = heat;
		}
	}

}
