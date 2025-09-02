package com.rosemods.heart_crystals.core.data.client;

import com.google.common.collect.Lists;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.HCTrimMaterials;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCEntityTypes;
import com.rosemods.heart_crystals.core.registry.HCItems;
import com.rosemods.heart_crystals.core.registry.HCPaintingVariants;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
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
        this.translateRegistry(ForgeRegistries.BLOCKS, Block::getDescriptionId);
    }

    private <T> void translateRegistry(IForgeRegistry<T> registry, Function<T, String> toString) {
        for (RegistryObject<T> object : HeartCrystals.REGISTRY_HELPER.getSubHelper(registry).getDeferredRegister().getEntries())
            this.add(toString.apply(object.get()), toUpper(registry, object));
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

    private void translatePainting(RegistryObject<PaintingVariant> painting, String author) {
        String name = ForgeRegistries.PAINTING_VARIANTS.getKey(painting.get()).getPath();
        this.add("painting." + HeartCrystals.MOD_ID + "." + name + ".title", toUpper(name));
        this.add("painting." + HeartCrystals.MOD_ID + "." + name + ".author", author);
    }

    private void translateBannerPattern(RegistryObject<? extends Item> item, String name) {
        String desc = toUpper(name);
        this.add(item.get(), "Banner Pattern");
        this.addDescription(item, desc);

        for (DyeColor dye : DyeColor.values())
            this.add("block.minecraft.banner." + HeartCrystals.MOD_ID + "." + name + "." + dye.getName(), toUpper(dye.getName()) + " " + desc);
    }

    private void addDescription(RegistryObject<? extends ItemLike> item, String desc) {
        this.add(item.get().asItem().getDescriptionId() + ".desc", desc);
    }

    private static <T> String toUpper(IForgeRegistry<T> registry, RegistryObject<? extends T> object) {
        return toUpper(registry.getKey(object.get()).getPath());
    }

    private static String toUpper(String string) {
        return StringUtils.capitaliseAllWords(string.replace('_', ' '));
    }

}
