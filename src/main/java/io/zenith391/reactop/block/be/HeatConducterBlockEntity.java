package io.zenith391.reactop.block.be;

import io.zenith391.reactop.registry.BlockRegistry;

public class HeatConducterBlockEntity extends AbstractHeatBlockEntity {
	
	public HeatConducterBlockEntity() {
		super(BlockRegistry.HEAT_CONDUCTER_ENTITY);
		this.isolation = 0.998d;
	}
	
}
