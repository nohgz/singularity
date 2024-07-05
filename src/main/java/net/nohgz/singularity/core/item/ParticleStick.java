package net.nohgz.singularity.core.item;

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
import net.nohgz.singularity.client.vfx.WorldParticleEffects;
import net.nohgz.singularity.client.vfx.ShakeEffects;
import net.nohgz.singularity.core.util.RayHelpers;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ParticleStick extends Item {

    public ParticleStick(Properties properties) {

        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult ray = RayHelpers.blockRayTraceResult(level, player, ClipContext.Fluid.NONE,10f);
        Vec3 lookPos = ray.getLocation();

        WorldParticleEffects.spawnThingParticles(level, lookPos);
        ShakeEffects.thugShaker(1,0.25f);

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1.0F, 2.0F);
        return super.use(level,player,hand);
    }
}
