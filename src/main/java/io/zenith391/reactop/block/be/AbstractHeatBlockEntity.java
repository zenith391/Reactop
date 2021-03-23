package io.zenith391.reactop.block.be;

import dev.onyxstudios.cca.api.v3.block.BlockComponents;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.ReactopComponents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractHeatBlockEntity extends BlockEntity implements Tickable {

	protected double isolation = 0.95d;
	
	public AbstractHeatBlockEntity(BlockEntityType<?> type) {
		super(type);
	}
	
	private void tryShare(BlockPos pos) {
		HeatComponent hc = BlockComponents.get(ReactopComponents.HEAT, world, pos);
		HeatComponent heat = BlockComponents.get(ReactopComponents.HEAT, this);
		if (hc != null) {
			heat.shareHeat(hc);
		}
	}
	
	public HeatComponent getHeatComponent() {
		return BlockComponents.get(ReactopComponents.HEAT, this);
	}
	
	public void tick() {
		if (!world.isClient) {
			BlockComponents.get(ReactopComponents.HEAT, this).ambientTemperature(isolation);
			tryShare(pos.down());
			tryShare(pos.up());
			tryShare(pos.north());
			tryShare(pos.east());
			tryShare(pos.west());
			tryShare(pos.south());
		}
	}

}
