package com.rosemods.heart_crystals.common.item;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.other.HCEvents;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class HeartCrystalItem extends BlockItem {
    public HeartCrystalItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);

        if (info.heartCount < HCConfig.COMMON.maximum.get()) {
            info.heartCount++;
            info.syncHealthInfo(player);
            HCEvents.setMaxHealthAttribute(info.heartCount * 2, player);
            stack.shrink(1);
            player.heal(2f);
            player.getCooldowns().addCooldown(this, 1);

            return InteractionResultHolder.success(stack);
        } else
            player.displayClientMessage(Component.translatable(this.getDescriptionId() + ".maximum"), true);

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.DARK_PURPLE));
    }

}
