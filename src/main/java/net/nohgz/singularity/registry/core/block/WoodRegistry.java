package net.nohgz.singularity.registry.core.block;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.common.Mod;
import net.nohgz.singularity.SingularityMod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = SingularityMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodRegistry {
    public static List<WoodType> WOOD_TYPES = new ArrayList<>();
    public static final WoodType GRAVIWOOD = WoodType.register(new SingularityWoodType("graviwood"));

    static class SingularityWoodType extends WoodType {
        public SingularityWoodType(String nameIn) {
            super("singularity:" + nameIn);
            WOOD_TYPES.add(this);
        }
    }
}


