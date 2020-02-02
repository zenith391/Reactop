package io.zenith391.reactop.block;

import java.util.Set;

import com.google.common.collect.Sets;

import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.block.be.HeatStorageBlockEntity;
import nerdhub.cardinal.components.api.BlockComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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
	
	public static final Identifier GUI_ID = new Identifier("reactop", "heat_storage_gui");
	
	public HeatStorage() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2.f)
				.build()
				);
	}
	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if (world.isClient) return ActionResult.SUCCESS;
		BlockEntity be = world.getBlockEntity(pos);
		if (be != null && be instanceof HeatStorageBlockEntity) {
			ContainerProviderRegistry.INSTANCE.openContainer(GUI_ID, player, (buf) -> {
				buf.writeBlockPos(pos);
			});
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		return type == ComponentTypes.HEAT_COMPONENT;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
			Direction side) {
		if (type == ComponentTypes.HEAT_COMPONENT) {
			return (T) ((HeatStorageBlockEntity) blockView.getBlockEntity(pos)).getHeatComponent();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
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
