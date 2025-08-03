package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.datapack.HCBannerPatterns;
import com.rosemods.heart_crystals.core.registry.datapack.HCBiomeModifiers;
import com.rosemods.heart_crystals.core.registry.datapack.HCPaintingVariants;
import com.rosemods.heart_crystals.core.registry.datapack.HCTrimMaterials;
import com.rosemods.heart_crystals.core.registry.HCFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;

public class HCDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, HCFeatures.Features::bootstrap)
            .add(Registries.PLACED_FEATURE, HCFeatures.Placements::bootstrap)
            .add(Registries.PAINTING_VARIANT, HCPaintingVariants::bootstrap)
            .add(Registries.BANNER_PATTERN, HCBannerPatterns::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, HCBiomeModifiers::bootstrap)
            .add(Registries.TRIM_MATERIAL, HCTrimMaterials::bootstrap);

    public HCDatapackProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), BUILDER, Set.of(HeartCrystals.MOD_ID, "minecraft"));
    }

}
