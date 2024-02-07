package net.nohgz.lodestonefun.common.item;

import com.mojang.math.Vector3d;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
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
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;

import java.awt.Color;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParticleStick extends Item {

    public ParticleStick(Properties properties) {

        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult ray = blockRayTraceResult(level, player, ClipContext.Fluid.NONE);
        Vec3 lookPos = ray.getLocation();

        spawnThingParticles(level, lookPos);

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1.0F, 2.0F);
        return super.use(level,player,hand);
    }

    protected static BlockHitResult blockRayTraceResult(Level world, Player player, ClipContext.Fluid fluidMode) {

        // grug want talk with big brain who made stupid variable

        double range = 30;

        float playerXRot = player.getXRot();
        float playerYRot = player.getYRot();

        Vec3 playerEyePosition = player.getEyePosition();

        float cosPlayerYRot = Mth.cos(-playerYRot * (Mth.PI / 180F) - Mth.PI);
        float sinPlayerYRot = Mth.sin(-playerYRot * (Mth.PI / 180F) - Mth.PI);
        float cosPlayerXRot = -Mth.cos(-playerXRot * Mth.PI / 180F);
        float sinPlayerXRot = Mth.sin(-playerXRot * Mth.PI / 180F);

        float directionX = sinPlayerYRot * cosPlayerXRot;
        float directionZ = cosPlayerYRot * cosPlayerXRot;

        Vec3 extendedVec3 = playerEyePosition.add((double)directionX * range, (double)sinPlayerXRot * range, (double)directionZ * range);

        return world.clip(new ClipContext(playerEyePosition, extendedVec3, ClipContext.Block.OUTLINE, fluidMode, player));
    }

    protected static void spawnThingParticles (Level level, Vec3 pos) {
        Color startingColor = new Color(174, 42,222);
        Color endingColor = new Color(206,122,235);
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor,endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f,0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(40)
                .addMotion(0,0.1f,0)
                .enableNoClip()
                .spawn(level,pos.x,pos.y,pos.z);
    }
}
