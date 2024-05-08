package net.nohgz.singularity.core.events;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SingularitySoundEvents {
    public static final SoundEvent SINGULARITY_PLACE = register("block.singularity.place");

    private static SoundEvent register(String pKey) {
        return Registry.register(Registry.SOUND_EVENT, pKey, new SoundEvent(new ResourceLocation(pKey)));
    }
}
