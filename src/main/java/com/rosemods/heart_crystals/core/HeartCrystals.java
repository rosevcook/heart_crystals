package com.rosemods.heart_crystals.core;

import com.rosemods.heart_crystals.core.data.client.HCLanguageProvider;
import com.rosemods.heart_crystals.core.data.client.HCModelProvider;
import com.rosemods.heart_crystals.core.data.client.HCSoundProvider;
import com.rosemods.heart_crystals.core.data.client.HCSpriteSourceProvider;
import com.rosemods.heart_crystals.core.data.server.HCDatapackBuiltinEntriesProvider;
import com.rosemods.heart_crystals.core.data.server.HCLootTableProvider;
import com.rosemods.heart_crystals.core.data.server.HCRecipeProvider;
import com.rosemods.heart_crystals.core.data.server.tags.*;
import com.rosemods.heart_crystals.core.other.HCClientSync;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import com.rosemods.heart_crystals.core.registry.*;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(HeartCrystals.MOD_ID)
public class HeartCrystals {
    public static final String MOD_ID = "heart_crystals";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(REGISTRY_HELPER.prefix(MOD_ID), () -> "1", "1"::equals, "1"::equals);

    public HeartCrystals() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        REGISTRY_HELPER.register(bus);
        HCBannerPatterns.BANNER_PATTERNS.register(bus);
        HCPaintingVariants.PAINTING_VARIANTS.register(bus);
        HCFeatures.FEATURES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::registerCapabilities);
        bus.addListener(this::dataSetup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            HCBlocks.setupTabEditors();
            HCItems.setupTabEditors();
        });

        context.registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        registerMessage();
        DataUtil.addMix(Potions.AWKWARD, HCBlocks.HEART_CRYSTAL_SHARD.get().asItem(), Potions.REGENERATION);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(HCPlayerInfo.PlayerHealthInfo.class);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        gen.addProvider(client, new HCLanguageProvider(event));
        gen.addProvider(client, new HCModelProvider(event));
        gen.addProvider(client, new HCSoundProvider(event));
        gen.addProvider(client, new HCSpriteSourceProvider(event));

        gen.addProvider(server, new HCLootTableProvider(event));
        gen.addProvider(server, new HCRecipeProvider(event));

        HCBlockTagProvider blockTags;
        HCDatapackBuiltinEntriesProvider dataPack;
        gen.addProvider(server, dataPack = new HCDatapackBuiltinEntriesProvider(event));
        gen.addProvider(server, blockTags = new HCBlockTagProvider(event));
        gen.addProvider(server, new HCItemTagProvider(event, blockTags, dataPack));
        gen.addProvider(server, new HCBannerPatternTagProvider(event, dataPack));
        gen.addProvider(server, new HCPaintingVariantTagsProvider(event, dataPack));
        gen.addProvider(server, new HCTrimMaterialTagsProvider(event, dataPack));
    }

    private static void registerMessage() {
        PACKET_HANDLER.registerMessage(0,
                HCPlayerInfo.PlayerHealthInfoSync.class,
                HCPlayerInfo.PlayerHealthInfoSync::buffer,
                HCPlayerInfo.PlayerHealthInfoSync::new,
                (msg, ctx) -> HCClientSync.receivePacket(msg, ctx)
        );
    }

}
