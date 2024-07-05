package net.nohgz.singularity.registry.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nohgz.singularity.core.item.BoomStick;
import net.nohgz.singularity.core.item.FlashStick;
import net.nohgz.singularity.core.item.ParticleStick;
import net.nohgz.singularity.registry.common.block.BlockRegistry;
import net.nohgz.singularity.registry.common.tabs.CreativeTabRegistry;

import static net.nohgz.singularity.SingularityMod.MODID;

@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static Item.Properties DEBUG_PROPERTIES() {
        return new Item.Properties().tab(CreativeTabRegistry.DEBUG).stacksTo(1);
    }

    public static Item.Properties FREAKY() {
        return new Item.Properties().tab(CreativeTabRegistry.FREAKY);
    }

    // debugging stuffs
    public static final RegistryObject<Item> PARTICLE_STICK = ITEMS.register("particle_stick", () -> new ParticleStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> BOOM_STICK = ITEMS.register("boom_stick", () -> new BoomStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> FLASH_STICK = ITEMS.register("flash_stick", () -> new FlashStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> SINGULARITY_BLOCK = ITEMS.register("singularity_block", () -> new BlockItem(BlockRegistry.SINGULARITY_BLOCK.get(), FREAKY()));
    public static final RegistryObject<Item> EVIL_BLOCK = ITEMS.register("evil_ahh_block", () -> new BlockItem(BlockRegistry.EVIL_BLOCK.get(), FREAKY()));
    public static final RegistryObject<Item> BOOM_BLOCK = ITEMS.register("explosive_ahh_block", () -> new BlockItem(BlockRegistry.BOOM_BLOCK.get(), DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> VOID_BLOCK = ITEMS.register("void_block", () -> new BlockItem(BlockRegistry.VOID_BLOCK.get(), DEBUG_PROPERTIES()));

    //region graviwood
    public static final RegistryObject<Item> GRAVIWOOD_LEAVES = ITEMS.register("graviwood_leaves", () -> new BlockItem(BlockRegistry.GRAVIWOOD_LEAVES.get(), FREAKY()));
    public static final RegistryObject<Item> STRIPPED_GRAVIWOOD_LOG = ITEMS.register("stripped_graviwood_log", () -> new BlockItem(BlockRegistry.STRIPPED_GRAVIWOOD_LOG.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_LOG = ITEMS.register("graviwood_log", () -> new BlockItem(BlockRegistry.GRAVIWOOD_LOG.get(), FREAKY()));
    public static final RegistryObject<Item> STRIPPED_GRAVIWOOD = ITEMS.register("stripped_graviwood_wood", () -> new BlockItem(BlockRegistry.STRIPPED_GRAVIWOOD.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD = ITEMS.register("graviwood_wood", () -> new BlockItem(BlockRegistry.GRAVIWOOD.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_PLANKS = ITEMS.register("graviwood_planks", () -> new BlockItem(BlockRegistry.GRAVIWOOD_PLANKS.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_SLAB = ITEMS.register("graviwood_slab", () -> new BlockItem(BlockRegistry.GRAVIWOOD_SLAB.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_STAIRS = ITEMS.register("graviwood_stairs", () -> new BlockItem(BlockRegistry.GRAVIWOOD_STAIRS.get(),FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_DOOR = ITEMS.register("graviwood_door", () -> new BlockItem(BlockRegistry.GRAVIWOOD_DOOR.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_TRAPDOOR = ITEMS.register("graviwood_trapdoor", () -> new BlockItem(BlockRegistry.GRAVIWOOD_TRAPDOOR.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_BUTTON = ITEMS.register("graviwood_button", () -> new BlockItem(BlockRegistry.GRAVIWOOD_BUTTON.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_PRESSURE_PLATE = ITEMS.register("graviwood_pressure_plate", () -> new BlockItem(BlockRegistry.GRAVIWOOD_PRESSURE_PLATE.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_FENCE = ITEMS.register("graviwood_fence", () -> new BlockItem(BlockRegistry.GRAVIWOOD_FENCE.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_FENCE_GATE = ITEMS.register("graviwood_fence_gate", () -> new BlockItem(BlockRegistry.GRAVIWOOD_FENCE_GATE.get(), FREAKY()));
    public static final RegistryObject<Item> GRAVIWOOD_SIGN = ITEMS.register("graviwood_sign", () -> new BlockItem(BlockRegistry.GRAVIWOOD_SIGN.get(), FREAKY()));

    public static final RegistryObject<Item> GRAVIWOOD_LAMP = ITEMS.register("graviwood_lamp", () -> new BlockItem(BlockRegistry.GRAVIWOOD_LAMP.get(), FREAKY()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
