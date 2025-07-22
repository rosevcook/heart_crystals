package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.level.gen.feature.HeartCrystalFeature;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
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
        public static final ResourceKey<ConfiguredFeature<?, ?>> HEART_CRYSTAL = createKey("heart_crystal");

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            context.register(HEART_CRYSTAL, new ConfiguredFeature<>(HEART_CRYSTAL_FEATURE.get(), NoneFeatureConfiguration.NONE));
        }

        private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, HeartCrystals.REGISTRY_HELPER.prefix(name));
        }

    }

    public static final class Placements {
        public static final ResourceKey<PlacedFeature> HEART_CRYSTAL = createKey("heart_crystal");

        public static void bootstrap(BootstapContext<PlacedFeature> context) {
            register(context, HEART_CRYSTAL, Features.HEART_CRYSTAL, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        }

        private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
            context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), List.of(modifiers)));
        }

        private static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, HeartCrystals.REGISTRY_HELPER.prefix(name));
        }
    }

}
