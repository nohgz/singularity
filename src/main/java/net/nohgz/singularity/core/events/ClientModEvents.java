package net.nohgz.singularity.core.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.client.vfx.shaderthings.FlamePostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.GrayscalePostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.InvertPostProcessor;
import net.nohgz.singularity.client.vfx.shaderthings.WavePostProcessor;
import team.lodestar.lodestone.systems.postprocess.PostProcessHandler;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SingularityMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        PostProcessHandler.addInstance(WavePostProcessor.INSTANCE);
        PostProcessHandler.addInstance(FlamePostProcessor.INSTANCE);
        PostProcessHandler.addInstance(GrayscalePostProcessor.INSTANCE);
        PostProcessHandler.addInstance(InvertPostProcessor.INSTANCE);
    }
}