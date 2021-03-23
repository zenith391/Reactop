package io.zenith391.reactop.block;

import java.util.Set;
import com.google.common.collect.Sets;
import io.zenith391.reactop.ComponentTypes;
import io.zenith391.reactop.block.be.HeatFurnaceBlockEntity;
import io.zenith391.reactop.gui.HeatFurnaceDescription;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class HeatFurnace extends Block implements BlockEntityProvider {
	
	private static final Material MATERIAL = new Material(
			MaterialColor.IRON,
			false,
			true,
			false,
			true,
			true,
			false,
			PistonBehavior.NORMAL
		);
	
	public static final Identifier GUI_ID = new Identifier("reactop", "heat_furnace_gui");
	public static final ScreenHandlerType<HeatFurnaceDescription> SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(GUI_ID, (syncId, inventory) -> {
		return new HeatFurnaceDescription(syncId, inventory, ScreenHandlerContext.EMPTY, null);
	});
	public static final BooleanProperty LIT = BooleanProperty.of("lit");
	
	public HeatFurnace() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2f));
		setDefaultState(getStateManager().getDefaultState()
			.with(Properties.HORIZONTAL_FACING, Direction.NORTH)
			.with(LIT, false)
		);
	}
	
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		HeatFurnaceBlockEntity be = (HeatFurnaceBlockEntity) world.getBlockEntity(pos);
		return be;
	}
	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if (!world.isClient) {
			player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
		}
		return ActionResult.SUCCESS;
	}
	
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactory) {
		stateFactory.add(Properties.HORIZONTAL_FACING);
		stateFactory.add(LIT);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new HeatFurnaceBlockEntity();
	}
	
}
