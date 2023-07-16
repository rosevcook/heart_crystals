package com.rosemods.heart_crystals.core.registry;

import com.rosemods.heart_crystals.common.item.HeartCrystalItem;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class HCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HeartCrystals.MODID);

    public static final RegistryObject<Item> HEART_CRYSTAL = ITEMS.register("heart_crystal", () -> new HeartCrystalItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.UNCOMMON).stacksTo(16)));

}
