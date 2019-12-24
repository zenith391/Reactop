package io.zenith391.reactop.block.be;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.render.block.entity.BlockEntityRenderer;

public class ReactorTankEntityRenderer extends BlockEntityRenderer<ReactorTankBlockEntity> {

	@Override
	public void render(ReactorTankBlockEntity tank, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		
		GlStateManager.popMatrix();
	}
	
}
