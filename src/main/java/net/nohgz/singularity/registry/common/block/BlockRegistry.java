package net.nohgz.singularity.registry.common.block;


import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.nohgz.singularity.SingularityMod.MODID;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> SINGULARITY_BLOCK = BLOCKS.register("singularity_block", () -> new Block(SingularityBlockProperties.WOOD_LIKE()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
