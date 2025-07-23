package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.HCBiomeModifiers;
import com.rosemods.heart_crystals.core.other.HCTrimMaterials;
import com.rosemods.heart_crystals.core.registry.HCFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class HCDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, HCFeatures.Features::bootstrap)
            .add(Registries.PLACED_FEATURE, HCFeatures.Placements::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, HCBiomeModifiers::bootstrap)
            .add(Registries.TRIM_MATERIAL, HCTrimMaterials::bootstrap);

    public HCDatapackBuiltinEntriesProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), BUILDER, Set.of(HeartCrystals.MOD_ID, "minecraft"));
    }

}
