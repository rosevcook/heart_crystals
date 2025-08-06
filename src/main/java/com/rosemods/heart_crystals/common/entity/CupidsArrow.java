package com.rosemods.heart_crystals.common.entity;

import com.rosemods.heart_crystals.core.registry.HCEntityTypes;
import com.rosemods.heart_crystals.core.registry.HCItems;
import com.rosemods.heart_crystals.core.registry.HCParticleTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CupidsArrow extends AbstractArrow {

    public CupidsArrow(EntityType<? extends CupidsArrow> type, Level level) {
        super(type, level);
    }

    public CupidsArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(HCEntityTypes.CUPIDS_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    public CupidsArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(HCEntityTypes.CUPIDS_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround && this.level().isClientSide && this.tickCount > 2) {
            Vec3 motion = this.getDeltaMovement();

            for (int i = 1; i < 3; i++) {
                double px = getX() - motion.x * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double py = getY() - motion.y * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double pz = getZ() - motion.z * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double mx = (Math.random() - .5d) * .03d - motion.x * .08d;
                double my = (Math.random() - .5d) * .03d - motion.y * .08d;
                double mz = (Math.random() - .5d) * .03d - motion.z * .08d;

                this.level().addParticle(HCParticleTypes.CUPIDS_ARROW.get(), px, py, pz, mx, my, mz);
            }
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        if (this.level().isClientSide)
            for (int i = 0; i < 6; ++i) {
                double d0 = this.level().random.nextGaussian() * 0.02D;
                double d1 = this.level().random.nextGaussian() * 0.02D;
                double d2 = this.level().random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.HEART, living.getRandomX(1.0D), living.getRandomY() + 0.5D, living.getRandomZ(1.0D), d0, d1, d2);
            }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return HCItems.CUPIDS_ARROW.get().getDefaultInstance();
    }

}
