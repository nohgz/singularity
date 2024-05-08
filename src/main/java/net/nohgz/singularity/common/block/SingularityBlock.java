package net.nohgz.singularity.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SingularityBlock extends Block {
    public SingularityBlock(Properties pProperties) {
        super(pProperties);
    }

}
