package com.rosemods.heart_crystals.core.registry.datapack;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class HCBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addFeature(context, "heart_crystal", BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES, HCFeatures.Placements.HEART_CRYSTAL);
    }

    @SafeVarargs
    private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, HeartCrystals.location(name)), modifier.get());
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }

}
