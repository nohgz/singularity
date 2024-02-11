package net.nohgz.lodestonefun.common.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.nohgz.lodestonefun.client.vfx.ScreenEffects;
import net.nohgz.lodestonefun.client.vfx.ShakeEffects;
import net.nohgz.lodestonefun.common.functional.RayHelpers;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.particle.screen.LodestoneScreenParticleRenderType;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleType;
import team.lodestar.lodestone.systems.particle.screen.base.ScreenParticle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static team.lodestar.lodestone.handlers.PlacementAssistantHandler.target;
import static team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry.*;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FlashStick extends Item {
    private float ArrayList;

    public FlashStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        /*
        BlockHitResult ray = RayHelpers.blockRayTraceResult(level, player, ClipContext.Fluid.NONE);
        Vec3 lookPos = ray.getLocation();
         */

        // ShakeEffects.thugShaker(20,0.5f);
        HashMap<LodestoneScreenParticleRenderType, ArrayList<ScreenParticle>> map = new HashMap<>();
        LodestoneScreenParticleRenderType key = LodestoneScreenParticleRenderType.ADDITIVE;
        ArrayList<ScreenParticle> value = new ArrayList<>();
        map.put(key, value);

        ScreenEffects.screenFlash(
            map,
            new Color(255, 255, 255),
            new Color(224, 9, 9),
            5f,
            5f
        );

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.CAT_AMBIENT, SoundSource.PLAYERS, 1.0F, 1F);
        return super.use(level,player,hand);
    }


}
