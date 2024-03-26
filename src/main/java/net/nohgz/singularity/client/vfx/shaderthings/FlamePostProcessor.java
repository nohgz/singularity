package net.nohgz.singularity.client.vfx.shaderthings;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.nohgz.singularity.SingularityMod;
import team.lodestar.lodestone.systems.postprocess.PostProcessor;

public class FlamePostProcessor extends PostProcessor {

    public static final FlamePostProcessor INSTANCE = new FlamePostProcessor();


    static {
        INSTANCE.setActive(false);
    }


    @Override
    public ResourceLocation getPostChainLocation() {
        return SingularityMod.singularityPath("flame_post");
    }

    @Override
    public void beforeProcess(PoseStack viewModelStack) {

    }

    @Override
    public void afterProcess() {

    }
}
