package net.nohgz.singularity.registry.client;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.client.particles.SliceParticle;

//@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SingularityMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {
    public static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SingularityMod.MODID);
    public static RegistryObject<SimpleParticleType> SLICE_PARTICLE = PARTICLES.register("slice", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactory(RegisterParticleProvidersEvent event) {
        event.register(SLICE_PARTICLE.get(), SliceParticle.Factory::new);
    }

}