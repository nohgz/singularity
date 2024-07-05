package net.nohgz.singularity.core.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class RayHelpers {
    public static BlockHitResult blockRayTraceResult(Level world, Player player, ClipContext.Fluid fluidMode, double range) {

        // grug want talk with big brain who made stupid variable

        if (range > 30) range = 30;

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
}
