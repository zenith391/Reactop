package io.zenith391.reactop;

public class HeatComponentImpl implements HeatComponent {

	protected double heat;
	protected double capacity = 1000;
	
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

}
