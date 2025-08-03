package com.rosemods.heart_crystals.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HeartCrystalBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    private static final VoxelShape SHAPE = box(2f, 0f, 2f, 14f, 15f, 14f);
    private static final VoxelShape SHAPE_HANGING = box(2f, 1f, 2f, 14f, 16f, 14f);

    public HeartCrystalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HANGING, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HANGING) ? SHAPE_HANGING : SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        boolean hanging = state.getValue(HANGING);
        return canSupportCenter(level, hanging ? pos.above() : pos.below(), hanging ? Direction.DOWN : Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState otherState, LevelAccessor level, BlockPos pos, BlockPos otherPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);

        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(HANGING, context.getClickedFace() == Direction.DOWN);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, HANGING);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        particles(level, pos, rand);
    }

    public static void particles(Level level, BlockPos pos, RandomSource rand) {
        double d0 = pos.getX() + rand.nextFloat();
        double d1 = pos.getY() + rand.nextFloat();
        double d2 = pos.getZ() + rand.nextFloat();
        double d3 = (.4f - (rand.nextFloat() + rand.nextFloat()) * .4f);

        if (rand.nextInt(6) == 0)
            level.addParticle(ParticleTypes.END_ROD, d0 + Direction.UP.getStepX() * d3, d1 + Direction.UP.getStepY() * d3,
                    d2 + Direction.UP.getStepZ() * d3, rand.nextGaussian() * .005d, rand.nextGaussian() * .005d, rand.nextGaussian() * .005d);
    }

}
