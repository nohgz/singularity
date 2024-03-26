package net.nohgz.singularity.client.vfx;

import net.minecraft.client.Minecraft;
import net.minecraft.util.RandomSource;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType;
import team.lodestar.lodestone.systems.particle.screen.base.ScreenParticle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenEffects {


    // Hashmap contains 🐱 LodestoneScreenParticleRenderType and ArrayList of type <ScreenParticle>
    public static void screenFlash(HashMap<LodestoneScreenParticleRenderType, ArrayList<ScreenParticle>> target,
                                   Color color, Color endColor, float pXPosition, float pYPosition) {
        RandomSource rand = Minecraft.getInstance().level.getRandom();

        ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.WISP, target)
            .setTransparencyData(GenericParticleData.create(0.04f, 0f).setEasing(Easing.SINE_IN_OUT).build())
            .setScaleData(GenericParticleData.create(0.8f + rand.nextFloat() * 0.1f, 0).setEasing(Easing.SINE_IN_OUT, Easing.BOUNCE_IN_OUT).build())
            .setColorData(ColorParticleData.create(color, endColor).setCoefficient(2f).build())
            .setLifetime(10 + rand.nextInt(10))
            .setRandomOffset(0.05f)
            .setRandomMotion(0.05f, 0.05f)
            .spawn(pXPosition,pYPosition)
        ;

    }
}
