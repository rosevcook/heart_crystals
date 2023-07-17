package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCItems;
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

public class HCLanguageProvider extends LanguageProvider {
    public HCLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator(), HeartCrystals.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //items
        this.translateItem(HCItems.HEART_CRYSTAL_SHARD);
        this.translateBannerPattern(HCItems.HEART_BANNER_PATTERN, "heart");

        //blocks
        this.translateBlock(HCBlocks.HEART_CRYSTAL);
        this.translateBlock(HCBlocks.HEART_LANTERN);
        this.addDescription(HCBlocks.HEART_CRYSTAL, "+1 Maximum Health");
        this.add(HCBlocks.HEART_CRYSTAL.get().getDescriptionId() + ".maximum", "Cannot use heart crystal; currently at maximum health!");
    }

    private void translateBannerPattern(RegistryObject<? extends Item> item, String name) {
        String desc = StringUtils.capitaliseAllWords(name.replace('_', ' '));
        this.add(item.get(), "Banner Pattern");
        this.addDescription(item, desc);

        for (DyeColor dye : DyeColor.values())
            this.add("block.minecraft.banner." + HeartCrystals.MODID + "." + name + "." + dye.getName(), StringUtils.capitaliseAllWords(dye.getName().replace('_', ' ')) + " " + desc);
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
