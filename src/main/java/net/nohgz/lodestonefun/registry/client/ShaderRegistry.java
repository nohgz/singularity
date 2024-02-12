package net.nohgz.lodestonefun.registry.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nohgz.lodestonefun.LodestoneFunMod;
import team.lodestar.lodestone.systems.rendering.shader.ShaderHolder;

import java.io.IOException;

import static team.lodestar.lodestone.registry.client.LodestoneShaderRegistry.registerShader;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = LodestoneFunMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShaderRegistry {

    public static ShaderHolder END_PORTAL = new ShaderHolder(LodestoneFunMod.lodestoneFunPath("rendertype_end_portal"), DefaultVertexFormat.POSITION_COLOR_TEX, "Speed", "Zoom", "Distortion", "Intensity", "Wibble");

    @SubscribeEvent
    public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
        ResourceManager resourceManager = event.getResourceManager();
        registerShader(event, END_PORTAL.createInstance(resourceManager));
    }
}