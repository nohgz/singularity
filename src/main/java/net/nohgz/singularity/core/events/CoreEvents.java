package net.nohgz.singularity.core.events;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.client.screen.SingularityCompressorScreen;
import net.nohgz.singularity.registry.core.screens.MenuRegistry;

public class CoreEvents {
    @Mod.EventBusSubscriber(modid = SingularityMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(MenuRegistry.SINGULARITY_COMPRESSOR_MENU.get(), SingularityCompressorScreen::new);

        }
    }
}
