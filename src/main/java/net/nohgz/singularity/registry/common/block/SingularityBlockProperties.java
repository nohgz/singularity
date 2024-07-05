package net.nohgz.singularity.registry.common.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.nohgz.singularity.core.block.LampBlock;
import team.lodestar.lodestone.systems.block.LodestoneBlockProperties;

public class SingularityBlockProperties
{
    public static LodestoneBlockProperties GRAVIWOOD()
    {
        return new LodestoneBlockProperties(Material.WOOD, MaterialColor.WOOD)
                .addTag(BlockTagRegistry.GRAVIWOOD)
                .needsAxe()
                .sound(SoundType.WOOD)
                .strength(1.75F, 2.0F)
                ;
    }

    public static LodestoneBlockProperties GRAVIWOOD_LAMP()
    {
        return new LodestoneBlockProperties(Material.WOOD, MaterialColor.WOOD)
                .addTag(BlockTagRegistry.GRAVIWOOD)
                .needsAxe()
                .sound(SoundType.WOOD)
                .strength(1.75F, 2.0F)
                .lightLevel(state->state.getValue(LampBlock.LIT) ? 15 : 0)
                ;
    }

    public static LodestoneBlockProperties GRAVIWOOD_LEAVES()
    {
        return new LodestoneBlockProperties(Material.LEAVES, MaterialColor.COLOR_BLACK)
                .addTag(BlockTagRegistry.GRAVIWOOD_LEAVES)
                .needsHoe()
                .sound(SoundType.AZALEA_LEAVES)
                .strength(0.25f,0f)
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
