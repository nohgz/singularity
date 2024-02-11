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
import java.util.Random;

public class WorldParticleEffects {
    public static void spawnThingParticles (Level level, Vec3 pos) {

        float MAGNITUDE = 0.5f;

        Color startingColor = new Color(174, 42,222);
        Color endingColor = new Color(206,122,235);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f).build())
                .setTransparencyData(GenericParticleData.create(1.0f, 0.0f).build())
                .setColorData(ColorParticleData.create(startingColor,endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f,0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(10)
                .addMotion(0,0.5,0)
                .setRandomMotion(MAGNITUDE,MAGNITUDE)
                .enableNoClip()
                .setGravity(2.0F)
                .spawn(level,pos.x,pos.y,pos.z);
    }

    public static void spawnBoomParticle (Level level, Vec3 pos) {

        Random rand = new Random();

        Color startingBoomColor = new Color(252, 151, 95);
        Color endingBoomColor = new Color(87, 19, 6);

        Color startingSmokeColor = new Color(28, 28, 28);
        Color endingSmokeColor = new Color(0, 0, 0);


        //actual boom
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(2f * rand.nextFloat(1.5f,2.5f)).build())
                .setTransparencyData(GenericParticleData.create(1.0f, 0.0f).build())
                .setColorData(ColorParticleData.create(startingBoomColor,endingBoomColor).setCoefficient(1.4f).setEasing(Easing.QUARTIC_OUT).build())
                .setSpinData(SpinParticleData.create(0.0f,0.01f).setSpinOffset((level.getGameTime() * 0.1f) % 6.28f).setEasing(Easing.QUARTIC_OUT).build())
                .setLifetime(60)
                .addMotion(0,0,0)
                .enableNoClip()
                .setGravity(0f)
                .setRandomOffset(9.0)
                .setRandomMotion(0.001f)
                .spawnAtRandomFace(level, new BlockPos(pos.x, pos.y, pos.z))
                .repeat(
                        level,
                        pos.x,
                        pos.y,
                        pos.z,
                        50
                )
        ;

        //aftermath smoke
        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create(rand.nextFloat(1.5f,2f), 3.5f * rand.nextFloat(1.5f,2f)).build())
                .setTransparencyData(GenericParticleData.create(1.0f, 0.0f).build())
                .setColorData(ColorParticleData.create(startingSmokeColor,endingSmokeColor).setCoefficient(1.4f).setEasing(Easing.QUARTIC_OUT).build())
                .setSpinData(SpinParticleData.create(0.0f,0.0f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_OUT).build())
                .setLifetime(350)
                .addMotion(0,0.12f,0)
                .enableNoClip()
                .setGravity(0.01f)
                .setRandomOffset(10.0)
                .spawn(level,pos.x,pos.y,pos.z)
                .repeat(
                        level,
                        pos.x,
                        pos.y,
                        pos.z,
                        100
                )

        ;


    }
}
