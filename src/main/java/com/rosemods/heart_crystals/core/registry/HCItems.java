package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.tags.HCBannerPatternTags;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCItems {
    public static final ItemSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> HEART_BANNER_PATTERN = HELPER.createItem("heart_banner_pattern", () -> new BannerPatternItem(HCBannerPatternTags.HEART, new Item.Properties().stacksTo(1)));

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(HeartCrystals.MOD_ID)
                .tab(CreativeModeTabs.INGREDIENTS)
                .addItemsAfter(Ingredient.of(Items.PIGLIN_BANNER_PATTERN), HEART_BANNER_PATTERN);
    }
}
