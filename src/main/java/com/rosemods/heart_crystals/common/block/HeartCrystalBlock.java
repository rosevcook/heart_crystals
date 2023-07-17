package com.rosemods.heart_crystals.common.block;

import com.teamabnormals.blueprint.common.block.InjectedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HeartCrystalBlock extends InjectedBlock {
    public HeartCrystalBlock(Properties properties) {
        super(Items.AMETHYST_SHARD, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        double d0 = pos.getX() + .55d - (rand.nextFloat() * .1f);
        double d1 = pos.getY() + .55d - (rand.nextFloat() * .1f);
        double d2 = pos.getZ() + .55d - (rand.nextFloat() * .1f);
        double d3 = (.4f - (rand.nextFloat() + rand.nextFloat()) * .4f);

        if (rand.nextInt(5) == 0)
            level.addParticle(ParticleTypes.END_ROD, d0 + Direction.UP.getStepX() * d3, d1 + Direction.UP.getStepY() * d3,
                    d2 + Direction.UP.getStepZ() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * .005d, rand.nextGaussian() * .005d);
    }

}
