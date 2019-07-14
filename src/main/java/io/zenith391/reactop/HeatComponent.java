package io.zenith391.reactop;

import nerdhub.cardinal.components.api.component.Component;

public class HeatComponent implements Component {

	double heat;
	
	public double getHeat() {
		return heat;
	}
	
	public void setHeat(double heat) {
		this.heat = heat;
	}
	
	/**
	 * Acts as if heat was shared, returns shared heat<br/>
	 * Note: This methods drains heat from the component.
	 * The more conducts tends to zero, the more energy will be shared to it
	 * @param conduct
	 * @return
	 */
	public double shareHeat(float conduct) {
		if (conduct < 0)
			conduct = 0;
		double shared = heat/(1+conduct);
		heat = heat - shared;
		return shared;
	}
	
}
