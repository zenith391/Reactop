package io.zenith391.reactop.block;

import java.util.Set;

import com.google.common.collect.Sets;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.block.be.HeatStorageBlockEntity;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class HeatStorage extends Block implements BlockComponentProvider, BlockEntityProvider  {
	
	private static final Material MATERIAL = new Material(
			MaterialColor.IRON,
			false,
			false,
			false,
			true,
			true,
			false,
			false,
			PistonBehavior.NORMAL
		);
	
	public HeatStorage() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2.f)
				.build()
				);
	}

	@Override
	public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		return type == ComponentTypes.HEAT_COMPONENT;
	}

	@Override
	public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		if (type == ComponentTypes.HEAT_COMPONENT) {
			return (T) ((HeatStorageBlockEntity) blockView.getBlockEntity(pos)).getHeatComponent();
		}
		return null;
	}

	@Override
	public Set<ComponentType<? extends Component>> getComponentTypes(BlockView blockView, BlockPos pos,
			Direction side) {
		return Sets.newHashSet(ComponentTypes.HEAT_COMPONENT);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new HeatStorageBlockEntity();
	}
	
}
