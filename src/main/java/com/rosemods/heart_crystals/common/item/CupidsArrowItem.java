package com.rosemods.heart_crystals.common.item;

import com.rosemods.heart_crystals.common.entity.CupidsArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CupidsArrowItem extends ArrowItem {

    public CupidsArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        CupidsArrow arrow = new CupidsArrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

}
