package net.nohgz.lodestonefun.registry.common.item.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.nohgz.lodestonefun.LodestoneFunMod;

import java.util.function.Supplier;

public class LodestoneFunTab extends CreativeModeTab {

    private final Supplier<? extends Item> icon;

    public LodestoneFunTab(String name, Supplier<? extends Item> icon) {
        super(LodestoneFunMod.MODID + "_" + name);
        this.icon = icon;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(icon.get());
    }
}
