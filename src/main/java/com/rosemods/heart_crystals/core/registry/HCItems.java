package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.item.HeartCrystalItem;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HeartCrystals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HCItems {
    public static final ItemSubRegistryHelper HELPER = HeartCrystals.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> HEART_CRYSTAL = HELPER.createItem("heart_crystal", () -> new HeartCrystalItem(HCBlocks.HEART_CRYSTAL.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(16)));

}
