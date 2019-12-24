package io.zenith391.reactop;

import java.util.function.Consumer;

import nerdhub.cardinal.components.api.component.Component;

public interface HeatComponent extends Component {
	
	public double getHeat();
	public void setHeat(double heat);
	public double addHeat(double heat);
	
	public double getCapacity();
	public void setCapacity(double capacity);
	
	public boolean canGetHeat(double curr);
	
	/**
	 * Handle when the block is supposed to melt. The double passed to the Consumer is the target heat.
	 * @param run
	 */
	public void setMeltdownHandler(Consumer<Double> consumer);
	
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
	
	public default void shareHeat(HeatComponent hc) {
		double share = shareHeat(1f);
		double share2 = hc.shareHeat(1f);
		hc.addHeat(share);
		addHeat(share2);
	}
	
	/**
	 * Apply ambient temperature
	 */
	public default void ambientTemperature() {
		if (getHeat() < 20d) {
			addHeat(0.5d);
		} else {
			addHeat(-0.05d);
		}
	}
	
}
