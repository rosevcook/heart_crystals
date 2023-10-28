package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.other.tags.HCBannerPatternTags;
import com.teamabnormals.blueprint.common.item.BlueprintBannerPatternItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCItems {
    public static final ItemSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> HEART_BANNER_PATTERN = HELPER.createItem("heart_banner_pattern", () -> new BlueprintBannerPatternItem(HCBannerPatternTags.HEART, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

}
