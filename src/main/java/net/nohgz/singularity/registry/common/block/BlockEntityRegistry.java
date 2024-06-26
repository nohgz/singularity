package net.nohgz.singularity.registry.common.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nohgz.singularity.common.block.blockentity.VoidBlockEntity;

import static net.nohgz.singularity.SingularityMod.MODID;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final RegistryObject<BlockEntityType<VoidBlockEntity>> VOID_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("void_block_entity", () -> BlockEntityType.Builder.of(VoidBlockEntity::new, BlockRegistry.VOID_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}


