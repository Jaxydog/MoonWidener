package dev.jaxydog.widemoon.mixin;

import dev.jaxydog.widemoon.MoonWidener;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

	@Unique
	private Matrix4f originalMatrix;

	@ModifyVariable(
		method = "Lnet/minecraft/client/render/WorldRenderer;renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
			ordinal = 0
		),
		ordinal = 1
	)
	private Matrix4f wideMoon$updateMatrix(Matrix4f in) {
		originalMatrix = in.copy();
		return in.copy();
	}

	@ModifyVariable(
		method = "Lnet/minecraft/client/render/WorldRenderer;renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
			ordinal = 1
		),
		ordinal = 1
	)
	private Matrix4f wideMoon$scaleMoon(Matrix4f in) {
		var copy = originalMatrix.copy();
		copy.multiply(Matrix4f.scale(MoonWidener.MOON_SIZE, 1.0f, MoonWidener.MOON_SIZE));
		originalMatrix = null;
		return copy;
	}
}
