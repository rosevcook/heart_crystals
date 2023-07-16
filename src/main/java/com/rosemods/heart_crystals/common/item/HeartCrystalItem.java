package com.rosemods.heart_crystals.common.item;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.other.HCEvents;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class HeartCrystalItem extends Item {
    public HeartCrystalItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);

        if (info.HeartCount < HCConfig.COMMON.maximum.get()) {
            info.HeartCount++;
            info.syncPlayerVariables(player);
            HCEvents.executeHealthCommand(info.HeartCount * 2, player);
            stack.shrink(1);

            return InteractionResultHolder.success(stack);
        } else
            player.displayClientMessage(Component.translatable(this.getDescriptionId() + ".maximum"), true);

        return InteractionResultHolder.fail(stack);

    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }

}
