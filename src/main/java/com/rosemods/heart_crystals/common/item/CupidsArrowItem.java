package com.rosemods.heart_crystals.common.item;

import com.rosemods.heart_crystals.common.entity.CupidsArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CupidsArrowItem extends ArrowItem {

    public CupidsArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity livingEntity) {
        return new CupidsArrow(level, livingEntity);
    }

}
