package com.rosemods.heart_crystals.core.data.client;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
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
        this.translateBlock(HCBlocks.HEART_CRYSTAL);
        this.translateBlock(HCBlocks.HEART_LANTERN);
        this.add(HCBlocks.HEART_CRYSTAL.get().getDescriptionId() + ".desc", "+1 Maximum Health");
        this.add(HCBlocks.HEART_CRYSTAL.get().getDescriptionId() + ".maximum", "Cannot use heart crystal; currently at maximum health!");
    }

    private void translateBlock(RegistryObject<? extends Block> block) {
        this.add(block.get(), toUpper(ForgeRegistries.BLOCKS, block));
    }

    private static <T> String toUpper(IForgeRegistry<T> entry, RegistryObject<? extends T> object) {
        return StringUtils.capitaliseAllWords(entry.getKey(object.get()).getPath().replace('_', ' '));
    }

}
