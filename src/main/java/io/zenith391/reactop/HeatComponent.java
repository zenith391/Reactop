package io.zenith391.reactop;

import nerdhub.cardinal.components.api.component.Component;

public interface HeatComponent extends Component {
	
	public double getHeat();
	public void setHeat(double heat);
	public double addHeat(double heat);
	
	public double getCapacity();
	public void setCapacity(double capacity);
	
	/**
	 * Acts as if heat was shared, returns shared heat<br/>
	 * Note: This methods drains heat from the component.
	 * The more conducts tends to zero, the more energy will be shared to it
	 * @param conduct
	 * @return
	 */
	public default double shareHeat(float conduct) {
		if (conduct < 0)
			conduct = 0;
		double shared = getHeat()/(1+conduct);
		setHeat(getHeat() - shared);
		return shared;
	}
	
}
