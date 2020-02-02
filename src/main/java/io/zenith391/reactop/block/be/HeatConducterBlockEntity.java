package io.zenith391.reactop.block.be;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.HeatComponent;
import io.zenith391.reactop.HeatComponentImpl;
import io.zenith391.reactop.registry.BlockRegistry;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public class HeatConducterBlockEntity extends BlockEntity implements Tickable {

	HeatComponent heat = new HeatComponentImpl();
	
	public HeatConducterBlockEntity() {
		super(BlockRegistry.HEAT_CONDUCTER_ENTITY);
		heat.setCapacity(500);
		heat.setMeltdownHandler((target) -> {
			world.setBlockState(pos, Blocks.LAVA.getDefaultState());
		});
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
	
	private void tryShare(BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockComponentProvider) {
			BlockComponentProvider provider = (BlockComponentProvider) state.getBlock();
			if (provider.hasComponent(world, pos, ComponentTypes.HEAT_COMPONENT, null)) {
				HeatComponent hc = provider.getComponent(world, pos, ComponentTypes.HEAT_COMPONENT, null);
				heat.shareHeat(hc);
			}
		}
	}

	@Override
	public void tick() {
		heat.ambientTemperature(1d);
		
		tryShare(pos.down());
		tryShare(pos.up());
		tryShare(pos.north());
		tryShare(pos.east());
		tryShare(pos.west());
		tryShare(pos.south());
	}
	
}
