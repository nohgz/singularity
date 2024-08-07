package net.nohgz.singularity.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.client.entity.renderer.SingularityCompressorBlockEntityRenderer;
import net.nohgz.singularity.client.vfx.shaderthings.FlamePostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.GrayscalePostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.InvertPostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.WavePostProcessor;
import net.nohgz.singularity.core.networking.ModMessages;
import net.nohgz.singularity.core.networking.packet.TextC2SPacket;
import net.nohgz.singularity.core.util.Keybindings;
import net.nohgz.singularity.registry.core.block.BlockEntityRegistry;
import team.lodestar.lodestone.systems.postprocess.PostProcessHandler;

public class ClientEvents {
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SingularityMod.MODID)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (Keybindings.SHADER_TOGGLE_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a key!"));
                InvertPostProcessor.toggleInvert();
                GrayscalePostProcessor.toggleGrayscale();
                ModMessages.sendToServer(new TextC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SingularityMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            PostProcessHandler.addInstance(WavePostProcessor.INSTANCE);
            PostProcessHandler.addInstance(FlamePostProcessor.INSTANCE);
            PostProcessHandler.addInstance(GrayscalePostProcessor.INSTANCE);
            PostProcessHandler.addInstance(InvertPostProcessor.INSTANCE);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybindings.SHADER_TOGGLE_KEY);
        }

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(BlockEntityRegistry.SINGULARITY_COMPRESSOR_BLOCK_ENTITY.get(),
                    SingularityCompressorBlockEntityRenderer::new);
        }
    }

}