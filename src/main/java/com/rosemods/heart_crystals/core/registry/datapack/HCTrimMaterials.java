package com.rosemods.heart_crystals.core.registry.datapack;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;

public final class HCTrimMaterials {
    public static final ResourceKey<TrimMaterial> HEART_CRYSTAL_SHARD = createKey("heart_crystal_shard");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, HEART_CRYSTAL_SHARD, HCBlocks.HEART_CRYSTAL_SHARD, Style.EMPTY.withColor(0xff5c73), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, HeartCrystals.location(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, DeferredHolder<? extends ItemLike, ? extends ItemLike> item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        context.register(key, new TrimMaterial(key.location().toString().replace(':', '_'), BuiltInRegistries.ITEM.getHolder(item.getId()).get(), -1f, overrides, Component.translatable(Util.makeDescriptionId("trim_material", key.location())).withStyle(style)));
    }

}
