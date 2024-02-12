package com.rosemods.heart_crystals.common.block_entity;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.registry.HCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class HeartLanternBlockEntity extends BlockEntity implements BlockEntityTicker<HeartLanternBlockEntity> {
    public HeartLanternBlockEntity(BlockPos pos, BlockState state) {
        super(HCBlockEntities.HEART_LANTERN.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, HeartLanternBlockEntity blockEntity) {
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(HCConfig.COMMON.regenRange.get())))
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, true, true));
    }

}
