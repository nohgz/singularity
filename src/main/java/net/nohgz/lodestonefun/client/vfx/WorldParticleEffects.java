package net.nohgz.lodestonefun.client.vfx;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;

import java.awt.*;

public class WorldParticleEffects {
    public static void spawnThingParticles (Level level, Vec3 pos) {

        float MAGNITUDE = 0.5f;

        Color startingColor = new Color(174, 42,222);
        Color endingColor = new Color(206,122,235);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).build())
                .setTransparencyData(GenericParticleData.create(1.0f, 1.0f).build())
                .setColorData(ColorParticleData.create(startingColor,endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f,0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(10)
                .addMotion(0,0.5,0)
                .setRandomMotion(MAGNITUDE,MAGNITUDE)
                .enableNoClip()
                .setGravity(2.0F)
                .spawn(level,pos.x,pos.y,pos.z);
    }
}
