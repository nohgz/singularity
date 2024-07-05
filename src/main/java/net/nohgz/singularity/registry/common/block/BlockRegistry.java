package net.nohgz.singularity.registry.common.block;


import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nohgz.singularity.core.block.*;
import team.lodestar.lodestone.systems.block.LodestoneLogBlock;
import team.lodestar.lodestone.systems.block.sign.LodestoneStandingSignBlock;
import team.lodestar.lodestone.systems.block.sign.LodestoneWallSignBlock;

import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.PressurePlateBlock.Sensitivity.EVERYTHING;
import static net.minecraftforge.common.Tags.Blocks.FENCE_GATES_WOODEN;
import static net.nohgz.singularity.SingularityMod.MODID;
import static net.nohgz.singularity.registry.common.block.BlockTagRegistry.GRAVIWOOD_LOGS;
import static net.nohgz.singularity.registry.common.block.BlockTagRegistry.STRIPPED_LOGS;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> SINGULARITY_BLOCK = BLOCKS.register("singularity_block", () -> new SingularityBlock(SingularityBlockProperties.GRAVIWOOD()));
    public static final RegistryObject<Block> EVIL_BLOCK = BLOCKS.register("evil_ahh_block", () -> new DamageBlock(SingularityBlockProperties.INFECTION()));
    public static final RegistryObject<Block> BOOM_BLOCK = BLOCKS.register("explosive_ahh_block", () -> new ExplosiveBlock(SingularityBlockProperties.GRAVIWOOD()));

    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block", () -> new VoidBlock(SingularityBlockProperties.INFECTION()));

    //region graviwood
    //public static final RegistryObject<Block> GRAVIWOOD_SAPLING = BLOCKS.register("graviwood_sapling", () -> new SaplingBlock(SingularityBlockProperties.GRAVIWOOD_SAPLING().setCutoutRenderType().randomTicks(), FeatureRegistry.GRAVIWOOD_TREE));
    public static final RegistryObject<Block> GRAVIWOOD_LEAVES = BLOCKS.register("graviwood_leaves", () -> new LeavesBlock(SingularityBlockProperties.GRAVIWOOD_LEAVES().setCutoutRenderType()));

    public static final RegistryObject<Block> STRIPPED_GRAVIWOOD_LOG = BLOCKS.register("stripped_graviwood_log", () -> new RotatedPillarBlock(SingularityBlockProperties.GRAVIWOOD().addTags(LOGS, STRIPPED_LOGS, GRAVIWOOD_LOGS)));
    public static final RegistryObject<Block> GRAVIWOOD_LOG = BLOCKS.register("graviwood_log", () -> new LodestoneLogBlock(SingularityBlockProperties.GRAVIWOOD().addTags(LOGS, GRAVIWOOD_LOGS), STRIPPED_GRAVIWOOD_LOG));
    public static final RegistryObject<Block> STRIPPED_GRAVIWOOD = BLOCKS.register("stripped_graviwood_wood", () -> new RotatedPillarBlock(SingularityBlockProperties.GRAVIWOOD().addTags(LOGS, STRIPPED_LOGS, GRAVIWOOD_LOGS)));
    public static final RegistryObject<Block> GRAVIWOOD = BLOCKS.register("graviwood_wood", () -> new LodestoneLogBlock(SingularityBlockProperties.GRAVIWOOD().addTags(LOGS, GRAVIWOOD_LOGS), STRIPPED_GRAVIWOOD));


    public static final RegistryObject<Block> GRAVIWOOD_PLANKS = BLOCKS.register("graviwood_planks", () -> new Block(SingularityBlockProperties.GRAVIWOOD().addTags(PLANKS)));
    public static final RegistryObject<Block> GRAVIWOOD_SLAB = BLOCKS.register("graviwood_slab", () -> new SlabBlock(SingularityBlockProperties.GRAVIWOOD().addTags(SLABS, WOODEN_SLABS)));
    public static final RegistryObject<Block> GRAVIWOOD_STAIRS = BLOCKS.register("graviwood_stairs", () -> new StairBlock(() -> GRAVIWOOD_PLANKS.get().defaultBlockState(), SingularityBlockProperties.GRAVIWOOD().addTags(STAIRS, WOODEN_STAIRS)));


    public static final RegistryObject<Block> GRAVIWOOD_DOOR = BLOCKS.register("graviwood_door", () -> new DoorBlock(SingularityBlockProperties.GRAVIWOOD().addTags(DOORS, WOODEN_DOORS).setCutoutRenderType().noOcclusion()));
    public static final RegistryObject<Block> GRAVIWOOD_TRAPDOOR = BLOCKS.register("graviwood_trapdoor", () -> new TrapDoorBlock(SingularityBlockProperties.GRAVIWOOD().addTags(TRAPDOORS, WOODEN_TRAPDOORS).setCutoutRenderType().noOcclusion()));

    public static final RegistryObject<Block> GRAVIWOOD_BUTTON = BLOCKS.register("graviwood_button", () -> new WoodButtonBlock(SingularityBlockProperties.GRAVIWOOD().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final RegistryObject<Block> GRAVIWOOD_PRESSURE_PLATE = BLOCKS.register("graviwood_pressure_plate", () -> new PressurePlateBlock(EVERYTHING, SingularityBlockProperties.GRAVIWOOD().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final RegistryObject<Block> GRAVIWOOD_FENCE = BLOCKS.register("graviwood_fence", () -> new FenceBlock(SingularityBlockProperties.GRAVIWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final RegistryObject<Block> GRAVIWOOD_FENCE_GATE = BLOCKS.register("graviwood_fence_gate", () -> new FenceGateBlock(SingularityBlockProperties.GRAVIWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));

    public static final RegistryObject<Block> GRAVIWOOD_SIGN = BLOCKS.register("graviwood_sign", () -> new LodestoneStandingSignBlock(SingularityBlockProperties.GRAVIWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission(), WoodRegistry.GRAVIWOOD));
    public static final RegistryObject<Block> GRAVIWOOD_WALL_SIGN = BLOCKS.register("graviwood_wall_sign", () -> new LodestoneWallSignBlock(SingularityBlockProperties.GRAVIWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission(), WoodRegistry.GRAVIWOOD));
    public static final RegistryObject<LampBlock> GRAVIWOOD_LAMP = BLOCKS.register("graviwood_lamp", () -> new LampBlock(SingularityBlockProperties.GRAVIWOOD_LAMP()));

    //endregion

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
