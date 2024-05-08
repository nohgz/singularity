package net.nohgz.singularity.client.vfx.shaderthings;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.nohgz.singularity.SingularityMod;
import team.lodestar.lodestone.systems.postprocess.PostProcessor;

public class GrayscalePostProcessor extends PostProcessor {

    public static final GrayscalePostProcessor INSTANCE = new GrayscalePostProcessor();


    static {
        INSTANCE.setActive(false);
    }



    @Override
    public ResourceLocation getPostChainLocation() {
        return SingularityMod.singularityPath("grayscale_post");
    }

    @Override
    public void beforeProcess(PoseStack viewModelStack) {

    }

    @Override
    public void afterProcess() {

    }
}
