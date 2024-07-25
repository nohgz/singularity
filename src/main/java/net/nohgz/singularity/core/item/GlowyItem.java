package net.nohgz.singularity.core.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.particle.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType;
import team.lodestar.lodestone.systems.particle.screen.base.ScreenParticle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType.ADDITIVE;
import static team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType.TRANSPARENT;

public class GlowyItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier {
    public GlowyItem(Properties pProperties) {
        super(pProperties);
    }


    int delayDude = 0;
    Random randy = new Random();
    @Override
    public void spawnParticles(HashMap<LodestoneScreenParticleRenderType, ArrayList<ScreenParticle>> target, Level level, float partialTick, ItemStack stack, float x, float y) {

        if (delayDude == 3) {
            ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.TWINKLE, target)
                    .setScaleData(GenericParticleData.create(0.5f).build())
                    .setColorData(ColorParticleData.create(Color.CYAN, Color.BLACK).build())
                    .setRandomOffset(5.0d)
                    .setRandomMotion(0.5d)
                    .spawn(x,y);
            delayDude = 0;
        }

        delayDude = randy.nextInt(0,6);
    }

}