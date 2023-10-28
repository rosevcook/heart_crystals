package com.rosemods.heart_crystals.common.level.gen.feature;

import com.rosemods.heart_crystals.common.block.HeartCrystalBlock;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HeartCrystalFeature extends Feature<NoneFeatureConfiguration> {
    public HeartCrystalFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = HCBlocks.HEART_CRYSTAL.get().defaultBlockState().setValue(HeartCrystalBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(rand));

        for (int x = -1; x <= 1; ++x)
            for (int z = -1; z <= 1; ++z)
                for (int y = -2; y <= 2; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (rand.nextInt(2) == 0 && pos.getY() < 0 && level.isEmptyBlock(pos) && state.canSurvive(level, pos))
                        return level.setBlock(pos, state, 2);
                }

        return false;
    }

}
