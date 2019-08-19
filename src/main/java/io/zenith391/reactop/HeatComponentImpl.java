package io.zenith391.reactop;

public class HeatComponentImpl implements HeatComponent {

	protected double heat;
	
	@Override
	public double getHeat() {
		return heat;
	}

	@Override
	public void setHeat(double heat) {
		this.heat = heat;
	}

}
