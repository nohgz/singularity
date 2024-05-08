package net.nohgz.singularity.registry.common.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import team.lodestar.lodestone.systems.block.LodestoneBlockProperties;

public class SingularityBlockProperties
{
    public static LodestoneBlockProperties WOOD_LIKE()
    {
        return new LodestoneBlockProperties(Material.WOOD, MaterialColor.WOOD)
                .addTag(BlockTagRegistry.WOOD_BLOCKS)
                .needsAxe()
                .sound(SoundType.WOOD)
                .strength(1.75F, 4.0F)
            ;
    }

}
