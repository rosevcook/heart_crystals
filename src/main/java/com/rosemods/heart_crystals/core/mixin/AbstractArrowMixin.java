package com.rosemods.heart_crystals.core.mixin;

import com.rosemods.heart_crystals.core.registry.HCEntityTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    /*
    @Shadow
    private int knockback;


    @Redirect(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))

    private boolean onHitEntity(Entity instance, DamageSource source, float i) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;

        if (arrow.getType() == HCEntityTypes.CUPIDS_ARROW.get() && instance instanceof LivingEntity entity) {
            Entity owner = arrow.getOwner();

            if (entity.isInvertedHealAndHarm())
                return instance.hurt(owner == null ? entity.damageSources().magic()
                        : entity.damageSources().indirectMagic(entity, owner), i * 1.5f);
            else if (entity instanceof Animal animal && !animal.isBaby())
                animal.setInLove(owner instanceof Player pl ? pl : null);

            entity.heal(6f);
            this.knockback = 1;
            return true;
        }

        return instance.hurt(source, i);
    }
*/
}
