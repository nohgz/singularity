package net.nohgz.singularity.registry.core.screens;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nohgz.singularity.SingularityMod;
import net.nohgz.singularity.client.screen.SingularityCompressorMenu;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SingularityMod.MODID);

    public static final RegistryObject<MenuType<SingularityCompressorMenu>> SINGULARITY_COMPRESSOR_MENU =
            registerMenuType(SingularityCompressorMenu::new, "singularity_compressor_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>>
    registerMenuType(IContainerFactory<T> factory, String pName) {
        return MENUS.register(pName, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
