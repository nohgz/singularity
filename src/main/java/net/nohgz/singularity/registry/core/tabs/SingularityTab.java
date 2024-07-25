package net.nohgz.singularity.registry.core.tabs;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.nohgz.singularity.SingularityMod;

import java.util.function.Supplier;

public class SingularityTab extends CreativeModeTab {

    private final Supplier<? extends Item> icon;

    public SingularityTab(String name, Supplier<? extends Item> icon) {
        super(SingularityMod.MODID + "_" + name);
        this.icon = icon;
    }


    @Override
    public ItemStack makeIcon() {
        return new ItemStack(icon.get());
    }
}
