package io.zenith391.reactop.block;

import java.util.Set;
import com.google.common.collect.Sets;
import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.block.be.HeatStorageBlockEntity;
import io.zenith391.reactop.gui.HeatStorageDescription;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
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
			PistonBehavior.NORMAL
		);
	
	public static final Identifier GUI_ID = new Identifier("reactop", "heat_storage_gui");
	public static final ScreenHandlerType<HeatStorageDescription> SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(GUI_ID, (syncId, inventory) -> {
		return new HeatStorageDescription(syncId, inventory, ScreenHandlerContext.EMPTY, null);
	});
	
	public HeatStorage() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2f));
	}
	
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		HeatStorageBlockEntity be = (HeatStorageBlockEntity) world.getBlockEntity(pos);
		return be;
	}
	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
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
