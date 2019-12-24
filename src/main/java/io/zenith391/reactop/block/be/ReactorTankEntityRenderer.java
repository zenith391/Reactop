package io.zenith391.reactop.block.be;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class ReactorTankEntityRenderer extends BlockEntityRenderer<ReactorTankBlockEntity> {

	public ReactorTankEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(ReactorTankBlockEntity tank, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push();
		
		matrices.pop();
	}
	
}
