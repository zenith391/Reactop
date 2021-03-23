package io.zenith391.reactop;

import java.util.function.Consumer;

import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

public interface HeatComponent extends ServerTickingComponent {
	
	public double getHeat();
	public void setHeat(double heat);
	public double addHeat(double heat);
	
	public double getCapacity();
	
	public double getConductivity();
	public void setConductivity(double conductivity);
	
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
	
	public static final double AMBIENT_HEAT_CONDUCTIVITY = 0.5d;
	
	/**
	 * Apply ambient temperature effect.
	 * @param efficiency When this is 0, no isolation is applied. When this is 1, max isolation is there and no heat loss / gain is made.
	 */
	public default void ambientTemperature(double isolation) {
		setHeat(getHeat() * isolation + 20d * (1 - isolation));
	}
	
}
