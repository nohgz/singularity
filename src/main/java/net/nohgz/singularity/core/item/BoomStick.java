package net.nohgz.singularity.core.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.nohgz.singularity.client.vfx.ShakeEffects;
import net.nohgz.singularity.client.vfx.WorldParticleEffects;
import net.nohgz.singularity.core.util.RayHelpers;
import net.nohgz.singularity.core.util.SillyHelpers;

import java.awt.*;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BoomStick extends Item {

    public BoomStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        BlockHitResult ray = RayHelpers.blockRayTraceResult(level, player, ClipContext.Fluid.NONE, 50f);
        Vec3 lookPos = ray.getLocation();

        Color explosionColor = new Color(238, 130, 238);

        WorldParticleEffects.spawnBoomParticle(level, lookPos, explosionColor);
        ShakeEffects.thugShaker(4,0.45f);

        // I should probably use like a mixin or something but eh
        SillyHelpers helpers = new SillyHelpers();
        helpers.nonParticleExplode(
                level,
                player,
                DamageSource.WITHER,
                null,
                lookPos.x,
                lookPos.y,
                lookPos.z,
                8.0f,
                false,
                Explosion.BlockInteraction.DESTROY
        );

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 0.5F);
        return super.use(level,player,hand);
    }
}
