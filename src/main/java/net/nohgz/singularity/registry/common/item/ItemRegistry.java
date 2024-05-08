package net.nohgz.singularity.registry.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nohgz.singularity.common.item.BoomStick;
import net.nohgz.singularity.common.item.FlashStick;
import net.nohgz.singularity.common.item.ParticleStick;
import net.nohgz.singularity.registry.common.block.BlockRegistry;
import net.nohgz.singularity.registry.common.tabs.CreativeTabRegistry;

import static net.nohgz.singularity.SingularityMod.MODID;

@SuppressWarnings("unused")
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static Item.Properties DEBUG_PROPERTIES() {
        return new Item.Properties().tab(CreativeTabRegistry.DEBUG).stacksTo(1);
    }

    // debugging stuffs
    public static final RegistryObject<Item> PARTICLE_STICK = ITEMS.register("particle_stick", () -> new ParticleStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> BOOM_STICK = ITEMS.register("boom_stick", () -> new BoomStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> FLASH_STICK = ITEMS.register("flash_stick", () -> new FlashStick(DEBUG_PROPERTIES()));
    public static final RegistryObject<Item> SINGULARITY_BLOCK = ITEMS.register("singularity_block", () -> new BlockItem(BlockRegistry.SINGULARITY_BLOCK.get(), DEBUG_PROPERTIES()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
