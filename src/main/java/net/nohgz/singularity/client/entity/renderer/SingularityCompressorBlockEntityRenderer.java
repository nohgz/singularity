package net.nohgz.singularity.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.core.block.blockentity.SingularityCompressorBlockEntity;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class SingularityCompressorBlockEntityRenderer implements BlockEntityRenderer<SingularityCompressorBlockEntity> {

    public static final ResourceLocation TEXTURE_LOCATION = SingularityMod.singularityPath("textures/block/evil_ahh_block.png");

    public SingularityCompressorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SingularityCompressorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        renderSphere(pPoseStack, pBufferSource, TEXTURE_LOCATION);
    }

    public void renderSphere(PoseStack pPoseStack, MultiBufferSource pBufferSource, ResourceLocation pTexture) {
        VFXBuilders.WorldVFXBuilder pBuilder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();
        VertexConsumer textureConsumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE_TRIANGLE.applyAndCache(pTexture));
        pBufferSource.getBuffer(LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE_TRIANGLE.applyAndCache(pTexture));
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.5f, 0.5f);
        pBuilder.renderSphere(textureConsumer, pPoseStack, 5f, 30, 30);
        pPoseStack.popPose();
    }
}


