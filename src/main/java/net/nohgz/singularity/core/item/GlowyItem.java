package net.nohgz.singularity.core.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.particle.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType;
import team.lodestar.lodestone.systems.particle.screen.base.ScreenParticle;

import java.util.ArrayList;
import java.util.HashMap;

public class GlowyItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier {
    public GlowyItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void spawnParticles(HashMap<LodestoneScreenParticleRenderType, ArrayList<ScreenParticle>> target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.TWINKLE, target).spawn(x,y);
    }

}
