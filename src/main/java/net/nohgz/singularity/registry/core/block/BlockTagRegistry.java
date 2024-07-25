package net.nohgz.singularity.registry.core.block;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.nohgz.singularity.SingularityMod;

public class BlockTagRegistry {

    public static final TagKey<Block> INFECTION_BLOCKS = singularityTag("infection_blocks");
    public static final TagKey<Block> STRIPPED_LOGS = singularityTag("stripped_logs");
    public static final TagKey<Block> GRAVIWOOD_LOGS = singularityTag("graviwood_logs");
    public static final TagKey<Block> GRAVIWOOD_LEAVES = singularityTag("graviwood_leaves");
    public static final TagKey<Block> GRAVIWOOD_LAMP = singularityTag("graviwood_lamp");
    public static final TagKey<Block> GRAVIWOOD = singularityTag("graviwood");



    private static TagKey<Block> modTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(path));
    }

    private static TagKey<Block> singularityTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, SingularityMod.singularityPath(path));
    }

}