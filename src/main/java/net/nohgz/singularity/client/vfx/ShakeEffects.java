package net.nohgz.singularity.client.vfx;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.screenshake.ScreenshakeInstance;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShakeEffects {
    public static void thugShaker(int duration, float intensity1, float intensity2, float intensity3) {
        ScreenshakeHandler.addScreenshake(new
                ScreenshakeInstance(duration)
                .setIntensity(intensity1, intensity2, intensity3)
                .setEasing(Easing.EXPO_IN, Easing.BOUNCE_OUT)
        );
    }
    public static void thugShaker(int duration, float intensity1) {
        ScreenshakeHandler.addScreenshake(new
                ScreenshakeInstance(duration)
                .setIntensity(intensity1)
                .setEasing(Easing.EXPO_IN, Easing.BOUNCE_OUT)
        );
    }

}
