package com.rosemods.heart_crystals.common.level.gen.feature;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class HeartCrystalFeature extends Feature<NoneFeatureConfiguration> {
    public HeartCrystalFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = HCBlocks.HEART_CRYSTAL.get().defaultBlockState();
        BlockPos desination = null;
        int airAmount = 0;

        for (int x = 0; x <= 6; ++x)
            for (int z = 0; z <= 6; ++z)
                for (int y = 0; y <= 6; ++y) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState newState = level.getBlockState(pos);

                    if (newState.is(HCBlocks.HEART_CRYSTAL.get()))
                        return false;
                    else if ((newState.getMaterial().isReplaceable() || level.isWaterAt(pos)) && !level.getFluidState(pos).is(Fluids.LAVA)) {
                        airAmount++;

                        if (pos.getY() < HCConfig.COMMON.maxYLevel.get() && state.canSurvive(level, pos))
                            desination = pos;
                    }
                }

        if (desination != null && rand.nextInt(216) > airAmount) {
            level.setBlock(desination, state.setValue(HeartCrystalBlock.WATERLOGGED, level.getFluidState(desination).is(Fluids.WATER)), 2);
            return true;
        }

        return false;
    }

}
