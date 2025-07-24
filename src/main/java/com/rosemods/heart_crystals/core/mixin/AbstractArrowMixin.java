package com.rosemods.heart_crystals.core.mixin;

import com.rosemods.heart_crystals.core.registry.HCEntityTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    @Redirect(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))

    private boolean onHitEntity(Entity instance, DamageSource source, float i) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;

        if (arrow.getType() == HCEntityTypes.CUPIDS_ARROW.get() && instance instanceof LivingEntity entity) {
            if (entity.isInvertedHealAndHarm()) {
                DamageSource newSource = arrow.getOwner() == null ? entity.damageSources().magic() : entity.damageSources().indirectMagic(entity, arrow.getOwner());
                return instance.hurt(newSource, i * 1.5f);
            } else
                entity.heal(6.0f);

            if (entity instanceof Animal animal && !animal.isBaby()) {
                Player player = null;
                if (arrow.getOwner() instanceof Player pl)
                    player = pl;

                animal.setInLove(player);
            }

            return true;
        }

        return instance.hurt(source, i);
    }

}
