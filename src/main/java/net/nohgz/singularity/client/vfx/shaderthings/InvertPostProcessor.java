package net.nohgz.singularity.client.vfx.shaderthings;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.nohgz.singularity.SingularityMod;
import team.lodestar.lodestone.systems.postprocess.PostProcessor;

public class InvertPostProcessor extends PostProcessor {

    public static final InvertPostProcessor INSTANCE = new InvertPostProcessor();

    private static boolean toggleInvert = false;

    static {
        INSTANCE.setActive(toggleInvert);
    }

    public static void toggleInvert() {
        toggleInvert = !toggleInvert;
        INSTANCE.setActive(toggleInvert);
    }

    @Override
    public ResourceLocation getPostChainLocation() {
        return SingularityMod.singularityPath("invert_post");
    }

    @Override
    public void beforeProcess(PoseStack viewModelStack) {
        // No changes needed here for the toggle functionality
    }

    @Override
    public void afterProcess() {
        // No changes needed here for the toggle functionality
    }
}