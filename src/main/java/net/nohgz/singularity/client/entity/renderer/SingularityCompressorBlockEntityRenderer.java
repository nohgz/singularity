package net.nohgz.singularity.client.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.core.block.blockentity.SingularityCompressorBlockEntity;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeProvider;

import static team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry.createGenericRenderType;

public class SingularityCompressorBlockEntityRenderer implements BlockEntityRenderer<SingularityCompressorBlockEntity> {


    public static final RenderTypeProvider END_PORTAL_THING = new RenderTypeProvider((texture) ->
            createGenericRenderType(texture.getNamespace() + ":" + "assets/singularity/textures/block/graviwood_planks.png",
                    DefaultVertexFormat.POSITION_COLOR_TEX, VertexFormat.Mode.TRIANGLES,
                    RenderType.CompositeState.builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEndPortalShader))
                        .setTransparencyState(new RenderStateShard.TransparencyStateShard("no_transparency_thing", RenderSystem::disableBlend, () -> {}))
                        .setTextureState(RenderStateShard.MultiTextureStateShard.builder()
                                .add(TheEndPortalRenderer.END_SKY_LOCATION, false, false)
                                .add(TheEndPortalRenderer.END_PORTAL_LOCATION, false, false).build())
                        .setCullState(new RenderStateShard.CullStateShard(false))
            ));

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
        VertexConsumer textureConsumer = RenderHandler.DELAYED_RENDER.getBuffer(END_PORTAL_THING.applyAndCache(pTexture));
        pBufferSource.getBuffer(LodestoneRenderTypeRegistry.SCROLLING_TEXTURE_TRIANGLE.applyAndCache(pTexture));
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.5f, 0.5f);
        pBuilder.renderSphere(textureConsumer, pPoseStack, 5f, 30, 30);
        pPoseStack.popPose();
    }
}


