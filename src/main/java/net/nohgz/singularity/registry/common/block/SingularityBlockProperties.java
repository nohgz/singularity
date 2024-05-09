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
                .strength(1.75F, 2.0F)
                ;
    }

    public static LodestoneBlockProperties INFECTION()
    {
        return new LodestoneBlockProperties(Material.SCULK, MaterialColor.COLOR_BLACK)
                .addTag(BlockTagRegistry.INFECTION_BLOCKS)
                .needsPickaxe()
                .sound(SoundType.SCULK)
                .strength(999999999.0F,18000000.0F )
                ;
    }

}
