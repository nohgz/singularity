package net.nohgz.lodestonefun.client.vfx;

import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.screenshake.ScreenshakeInstance;


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
