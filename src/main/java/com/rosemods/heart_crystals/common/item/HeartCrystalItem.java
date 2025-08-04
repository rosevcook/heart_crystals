package com.rosemods.heart_crystals.common.item;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.other.HCEvents;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import com.rosemods.heart_crystals.core.registry.HCSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
        ItemStack stack = player.getItemInHand(hand);

        if (info.heartCount < HCConfig.COMMON.maximum.get()) {
            //player.sendSystemMessage(Component.literal("hearts: " + info.heartCount + " -> " + (info.heartCount + 1)));
            info.heartCount++;
            info.syncHealthInfo(player);
            HCEvents.setMaxHealthAttribute(info.heartCount * 2, player);

            player.heal(2f);
            level.playSound(player, player.blockPosition(), HCSoundEvents.HEART_CRYSTAL_USE.get(), SoundSource.PLAYERS, .65f, 1f + ((level.random.nextFloat() - .5f) / 8f));

            player.getCooldowns().addCooldown(this, 24);
            player.awardStat(Stats.ITEM_USED.get(this));
            stack.consume(1, player);

            return InteractionResultHolder.consume(stack);
        } else {
            player.displayClientMessage(Component.translatable(this.getDescriptionId() + ".maximum"), true);
            return InteractionResultHolder.fail(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(this.getDescriptionId() + ".desc", HCConfig.COMMON.maximum.get() + "").withStyle(ChatFormatting.DARK_PURPLE));
    }

}
