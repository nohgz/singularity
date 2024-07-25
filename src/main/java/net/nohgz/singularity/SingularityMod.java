package net.nohgz.singularity;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nohgz.singularity.core.networking.ModMessages;
import net.nohgz.singularity.registry.core.block.BlockEntityRegistry;
import net.nohgz.singularity.registry.core.block.BlockRegistry;
import net.nohgz.singularity.registry.core.item.ItemRegistry;
import net.nohgz.singularity.registry.core.screens.MenuRegistry;
import org.slf4j.Logger;

import static net.nohgz.singularity.registry.client.ParticleRegistry.PARTICLES;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(SingularityMod.MODID)
public class SingularityMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "singularity";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SingularityMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        BlockEntityRegistry.register(modEventBus);
        MenuRegistry.register(modEventBus);
        PARTICLES.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::networkSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation singularityPath(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void networkSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(ModMessages::register);
    }

}
