package com.rosemods.heart_crystals.core.data.client;

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

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.HEART_BANNER_PATTERN;

public class HCLanguageProvider extends LanguageProvider {
    public HCLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //items
        this.translateBannerPattern(HEART_BANNER_PATTERN, "heart");

        //blocks
        this.translateBlock(HEART_CRYSTAL);
        this.translateBlock(HEART_LANTERN);
        this.translateBlock(HEART_CRYSTAL_SHARD);
        this.addDescription(HEART_CRYSTAL, "+1 Permanent Heart (Max %s)");
        this.add(HEART_CRYSTAL.get().getDescriptionId() + ".maximum", "Cannot use heart crystal; currently at maximum hearts!");
    }

    private void translateBannerPattern(RegistryObject<? extends Item> item, String name) {
        String desc = StringUtils.capitaliseAllWords(name.replace('_', ' '));
        this.add(item.get(), "Banner Pattern");
        this.addDescription(item, desc);

        for (DyeColor dye : DyeColor.values())
            this.add("block.minecraft.banner." + HeartCrystals.MOD_ID + "." + name + "." + dye.getName(),
                    StringUtils.capitaliseAllWords(dye.getName().replace('_', ' ')) + " " + desc);
    }

    private void addDescription(RegistryObject<? extends ItemLike> item, String desc) {
        this.add(item.get().asItem().getDescriptionId() + ".desc", desc);
    }

    private void translateItem(RegistryObject<? extends Item> item) {
        this.add(item.get(), toUpper(ForgeRegistries.ITEMS, item));
    }

    private void translateBlock(RegistryObject<? extends Block> block) {
        this.add(block.get(), toUpper(ForgeRegistries.BLOCKS, block));
    }

    private static <T> String toUpper(IForgeRegistry<T> entry, RegistryObject<? extends T> object) {
        return StringUtils.capitaliseAllWords(entry.getKey(object.get()).getPath().replace('_', ' '));
    }

}
