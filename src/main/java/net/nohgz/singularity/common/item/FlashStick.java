package net.nohgz.singularity.common.item;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FlashStick extends Item {
    private boolean shaderToggle = true;

    public FlashStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide() && hand.equals(InteractionHand.MAIN_HAND))
        {
            System.out.println("ONE CLICKY!!!");
        }

        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.CAT_AMBIENT, SoundSource.PLAYERS, 1.0F, 1F);
        return super.use(level,player,hand);
    }

    private void setShaderToggle(boolean toggle) {
        this.shaderToggle = toggle;
    }

    private boolean getShaderToggle() {
        return this.shaderToggle;
    }
}
