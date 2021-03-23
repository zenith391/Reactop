package io.zenith391.reactop.block;

import io.zenith391.reactop.block.be.BoilerBlockEntity;
import io.zenith391.reactop.gui.HeatStorageDescription;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Boiler extends Block implements BlockEntityProvider  {
	
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
	
	public static final Identifier GUI_ID = new Identifier("reactop", "boiler_gui");
	public static final ScreenHandlerType<HeatStorageDescription> SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(GUI_ID, (syncId, inventory) -> {
		return new HeatStorageDescription(syncId, inventory, ScreenHandlerContext.EMPTY, null);
	});
	
	public Boiler() {
		super(FabricBlockSettings.of(MATERIAL)
				.hardness(2f));
	}
	
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BoilerBlockEntity be = (BoilerBlockEntity) world.getBlockEntity(pos);
		return be;
	}
	
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
		return ActionResult.SUCCESS;
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new BoilerBlockEntity();
	}
	
}
