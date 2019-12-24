package io.zenith391.reactop.block.be;

import io.zenith391.reactop.registry.BlockRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class ReactorTankBlockEntity extends BlockEntity {

	public ReactorTankBlockEntity() {
		super(BlockRegistry.REACTOR_TANK_ENTITY);
	}
	
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		return tag;
	}
	
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		
	}

}
