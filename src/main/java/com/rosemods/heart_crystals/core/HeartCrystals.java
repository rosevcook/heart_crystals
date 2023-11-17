package com.rosemods.heart_crystals.core;

import com.rosemods.heart_crystals.core.data.client.HCLanguageProvider;
import com.rosemods.heart_crystals.core.data.client.HCModelProvider;
import com.rosemods.heart_crystals.core.data.client.HCSoundProvider;
import com.rosemods.heart_crystals.core.data.server.HCLootTableProvider;
import com.rosemods.heart_crystals.core.data.server.HCRecipeProvider;
import com.rosemods.heart_crystals.core.data.server.modifiers.HCBiomeModifier;
import com.rosemods.heart_crystals.core.data.server.tags.HCBannerPatternTagProvider;
import com.rosemods.heart_crystals.core.data.server.tags.HCBlockTagProvider;
import com.rosemods.heart_crystals.core.data.server.tags.HCPaintingVariantTagsProvider;
import com.rosemods.heart_crystals.core.other.HCClientSync;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import com.rosemods.heart_crystals.core.registry.HCBannerPatterns;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import com.rosemods.heart_crystals.core.registry.HCFeatures;
import com.rosemods.heart_crystals.core.registry.HCPaintingVariants;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.net.http.HttpClient;

@Mod(HeartCrystals.MODID)
public class HeartCrystals {
    public static final String MODID = "heart_crystals";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(REGISTRY_HELPER.prefix(MODID), () -> "1", "1"::equals, "1"::equals);

    public HeartCrystals() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        REGISTRY_HELPER.register(bus);
        HCBannerPatterns.BANNER_PATTERNS.register(bus);
        HCPaintingVariants.PAINTING_VARIANTS.register(bus);
        HCFeatures.FEATURES.register(bus);
        HCFeatures.Features.CONFIGURED_FEATURES.register(bus);
        HCFeatures.Placements.PLACED_FEATURES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::registerCapabilities);
        bus.addListener(this::dataSetup);

        context.registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // not a lambda so class gets not loaded server-side
        //noinspection Convert2MethodRef
        PACKET_HANDLER.registerMessage(0,
                HCPlayerInfo.PlayerHealthInfoSync.class,
                HCPlayerInfo.PlayerHealthInfoSync::buffer,
                HCPlayerInfo.PlayerHealthInfoSync::new,
                (msg, ctx) -> HCClientSync.receivePacket(msg, ctx)
        );
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

        gen.addProvider(server, new HCLootTableProvider(event));
        gen.addProvider(server, new HCRecipeProvider(event));
        gen.addProvider(server, new HCBlockTagProvider(event));
        gen.addProvider(server, new HCBannerPatternTagProvider(event));
        gen.addProvider(server, new HCPaintingVariantTagsProvider(event));
        gen.addProvider(server, HCBiomeModifier.register(event));
    }

}
