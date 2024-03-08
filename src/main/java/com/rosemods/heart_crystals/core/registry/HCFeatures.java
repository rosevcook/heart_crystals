package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.level.gen.feature.HeartCrystalFeature;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public final class HCFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, HeartCrystals.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> HEART_CRYSTAL_FEATURE = FEATURES.register("heart_crystal", HeartCrystalFeature::new);

    public static final class Features {
        public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, HeartCrystals.MOD_ID);

        public static final RegistryObject<ConfiguredFeature<?, ?>> HEART_CRYSTAL = CONFIGURED_FEATURES.register("heart_crystal", () -> new ConfiguredFeature<>(HEART_CRYSTAL_FEATURE.get(), NoneFeatureConfiguration.NONE));

    }

    public static final class Placements {
        public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, HeartCrystals.MOD_ID);

        public static final RegistryObject<PlacedFeature> HEART_CRYSTAL = register("heart_crystal", Features.HEART_CRYSTAL, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

        public static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
            return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) configuredFeature.getHolder().get(), List.of(placementModifiers)));
        }

    }

}
