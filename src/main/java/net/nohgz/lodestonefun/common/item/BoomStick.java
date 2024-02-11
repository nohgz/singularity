package net.nohgz.lodestonefun.common.item;

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
import net.nohgz.lodestonefun.client.vfx.ShakeEffects;
import net.nohgz.lodestonefun.client.vfx.WorldParticleEffects;
import net.nohgz.lodestonefun.common.functional.RayHelpers;
import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.screenshake.ScreenshakeInstance;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BoomStick extends Item {
    public BoomStick(Properties properties) {

        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult ray = RayHelpers.blockRayTraceResult(level, player, ClipContext.Fluid.NONE, 50f);
        Vec3 lookPos = ray.getLocation();

        WorldParticleEffects.spawnBoomParticle(level, lookPos);
        ShakeEffects.thugShaker(5,0.45f);

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 0.5F);
        return super.use(level,player,hand);
    }


}
