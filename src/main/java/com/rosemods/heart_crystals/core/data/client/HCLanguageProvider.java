package com.rosemods.heart_crystals.core.data.client;

import com.google.common.collect.Lists;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCEntityTypes;
import com.rosemods.heart_crystals.core.registry.HCItems;
import com.rosemods.heart_crystals.core.registry.datapack.HCPaintingVariants;
import com.rosemods.heart_crystals.core.registry.datapack.HCTrimMaterials;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.codehaus.plexus.util.StringUtils;

import java.util.List;
import java.util.function.Function;


public class HCLanguageProvider extends LanguageProvider {
    private final List<String> keys = Lists.newArrayList();

    public HCLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), HeartCrystals.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // items
        this.translateBannerPattern(HCItems.HEART_BANNER_PATTERN, "heart");
        this.add(HCItems.CUPIDS_ARROW.get(), "Cupid's Arrow");

        // blocks
        this.addDescription(HCBlocks.HEART_CRYSTAL, "+1 Permanent Heart (Max %s)");
        this.add(HCBlocks.HEART_CRYSTAL.get().getDescriptionId() + ".maximum", "Cannot use heart crystal; currently at maximum hearts!");

        // entities
        this.add(HCEntityTypes.CUPIDS_ARROW.get(), "Cupid's Arrow");

        // paintings
        this.translatePainting(HCPaintingVariants.HEARTBEAT, "Yapettoshen");

        // trim materials
        this.translateTrimMaterial(HCTrimMaterials.HEART_CRYSTAL_SHARD, "Heart Crystal Material");

        // auto translation
        this.translateRegistry(Registries.BLOCK, Block::getDescriptionId);
    }

    private <T> void translateRegistry(ResourceKey<Registry<T>> registry, Function<T, String> toString) {
        for (DeferredHolder<?, ?> object : HeartCrystals.REGISTRY_HELPER.getSubHelper(registry).getDeferredRegister().getEntries())
            this.add(toString.apply((T) object.get()), toUpper(object));
    }

    private void translateTrimMaterial(ResourceKey<TrimMaterial> material, String name) {
        this.add("trim_material." + material.location().toString().replace(':', '.'), name);
    }

    @Override
    public void add(String key, String value) {
        if (!this.keys.contains(key)) {
            super.add(key, value);
            this.keys.add(key);
        }
    }

    private void translatePainting(ResourceKey<PaintingVariant> painting, String author) {
        String name = painting.location().getPath();
        this.add("painting." + HeartCrystals.MOD_ID + "." + name + ".title", toUpper(name));
        this.add("painting." + HeartCrystals.MOD_ID + "." + name + ".author", author);
    }

    private void translateBannerPattern(DeferredHolder<? extends Item, ? extends Item> item, String name) {
        String desc = toUpper(name);
        this.add(item.get(), "Banner Pattern");
        this.addDescription(item, desc);

        for (DyeColor dye : DyeColor.values())
            this.add("block.minecraft.banner." + HeartCrystals.MOD_ID + "." + name + "." + dye.getName(), toUpper(dye.getName()) + " " + desc);
    }

    private void addDescription(DeferredHolder<? extends ItemLike, ? extends ItemLike> item, String desc) {
        this.add(item.get().asItem().getDescriptionId() + ".desc", desc);
    }

    private static <T> String toUpper(DeferredHolder<?, ?> object) {
        return toUpper(object.getId().getPath());
    }

    private static String toUpper(String string) {
        return StringUtils.capitaliseAllWords(string.replace('_', ' '));
    }

}
