package net.nohgz.singularity.core.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FlashStick extends GlowyItem {
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.literal("try and do something with a shader weeee").withStyle(ChatFormatting.DARK_BLUE));
        } else {
            pTooltipComponents.add(Component.literal("Press shift or more info").withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
