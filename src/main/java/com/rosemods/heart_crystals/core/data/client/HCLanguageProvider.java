package com.rosemods.heart_crystals.core.data.client;

import com.google.common.collect.Lists;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
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

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.*;

public class HCLanguageProvider extends LanguageProvider {
    private final List<String> keys = Lists.newArrayList();

    public HCLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //items
        this.translateBannerPattern(HEART_BANNER_PATTERN, "heart");

        //blocks
        this.addDescription(HEART_CRYSTAL, "+1 Permanent Heart (Max %s)");
        this.add(HEART_CRYSTAL.get().getDescriptionId() + ".maximum", "Cannot use heart crystal; currently at maximum hearts!");

        // auto translation
        this.translateRegistry(ForgeRegistries.BLOCKS, Block::getDescriptionId);
    }

    private <T> void translateRegistry(IForgeRegistry<T> registry, Function<T, String> toString) {
        for (RegistryObject<T> object : HeartCrystals.REGISTRY_HELPER.getSubHelper(registry).getDeferredRegister().getEntries())
            this.add(toString.apply(object.get()), toUpper(registry, object));
    }

    @Override
    public void add(String key, String value) {
        if (!this.keys.contains(key)) {
            super.add(key, value);
            this.keys.add(key);
        }
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
