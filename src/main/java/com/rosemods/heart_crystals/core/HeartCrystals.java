package com.rosemods.heart_crystals.core;

import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HeartCrystals.MODID)
public class HeartCrystals {
    public static final String MODID = "heart_crystals";

    public HeartCrystals() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        HCBlocks.BLOCKS.register(bus);
        HCItems.ITEMS.register(bus);

        context.registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_SPEC);
    }


}
