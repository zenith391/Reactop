package io.zenith391.reactop.block.be;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.HeatComponentImpl;
import io.zenith391.reactop.registry.BlockRegistry;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public class HeatStorageBlockEntity extends BlockEntity implements Tickable {

	HeatComponent heat = new HeatComponentImpl();
	
	public HeatStorageBlockEntity() {
		super(BlockRegistry.HEAT_STORAGE_ENTITY);
		heat.setCapacity(10000);
	}
	
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.putDouble("heat", heat.getHeat());
		return tag;
	}
	
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		heat.setHeat(tag.getDouble("heat"));
	}

	public HeatComponent getHeatComponent() {
		return heat;
	}
	
	private void tryShare(BlockState state, BlockPos pos) {
		if (state.getBlock() instanceof BlockComponentProvider) {
			BlockComponentProvider provider = (BlockComponentProvider) state.getBlock();
			if (provider.hasComponent(world, pos, ComponentTypes.HEAT_COMPONENT, null)) {
				HeatComponent hc = provider.getComponent(world, pos, ComponentTypes.HEAT_COMPONENT, null);
				if (hc.getHeat() < 500d) {
					double shared = heat.shareHeat(1);
					double consumed = hc.addHeat(shared);
					if (consumed < shared) {
						heat.addHeat(shared - consumed);
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		
		if (heat.getHeat() < 293d) { // arbitrary Minecraft ambient temperature
			heat.addHeat(1d);
		}
		
		tryShare(world.getBlockState(pos.down()), pos.down());
		tryShare(world.getBlockState(pos.up()), pos.up());
		tryShare(world.getBlockState(pos.north()), pos.north());
		tryShare(world.getBlockState(pos.east()), pos.east());
		tryShare(world.getBlockState(pos.west()), pos.west());
		tryShare(world.getBlockState(pos.south()), pos.south());
		
	}
	
}
