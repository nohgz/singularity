package net.nohgz.singularity.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.nohgz.singularity.client.vfx.ShakeEffects;
import net.nohgz.singularity.client.vfx.WorldParticleEffects;
import net.nohgz.singularity.common.functional.RayHelpers;
import net.nohgz.singularity.common.functional.SillyHelpers;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BoomStick extends Item {
    private final List<Color> rainbowColors;
    private int colorIndex;

    public BoomStick(Properties properties) {
        super(properties);
        rainbowColors = new ArrayList<>(List.of(
                new Color(255, 91, 91),    // Red
                new Color(255, 203, 108),  // Orange
                new Color(255, 255, 126),  // Yellow
                new Color(130, 255, 130),    // Green
                new Color(133, 133, 255),    // Blue
                new Color(196, 116, 255),   // Indigo
                new Color(238, 130, 238) // Violet
        ));
        colorIndex = 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        BlockHitResult ray = RayHelpers.blockRayTraceResult(level, player, ClipContext.Fluid.NONE, 50f);
        Vec3 lookPos = ray.getLocation();

        Color explosionColor = rainbowColors.get(colorIndex);
        colorIndex = (colorIndex + 1) % rainbowColors.size(); // Loop back when reaching the end

        WorldParticleEffects.spawnBoomParticle(level, lookPos, explosionColor);
        ShakeEffects.thugShaker(4,0.45f);

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
