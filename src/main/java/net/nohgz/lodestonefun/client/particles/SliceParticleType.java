package net.nohgz.lodestonefun.client.particles;

import com.mojang.serialization.Codec;

import net.minecraft.core.particles.ParticleType;
import team.lodestar.lodestone.systems.particle.world.WorldParticleOptions;

public class SliceParticleType extends ParticleType<WorldParticleOptions> {
    public SliceParticleType() {
        super(false, WorldParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<WorldParticleOptions> codec() {
        return WorldParticleOptions.codecFor(this);
    }
}
