package com.rosemods.heart_crystals.core;

import com.rosemods.heart_crystals.core.data.client.HCLanguageProvider;
import com.rosemods.heart_crystals.core.data.server.HCLootTableProvider;
import com.rosemods.heart_crystals.core.data.server.HCRecipeProvider;
import com.rosemods.heart_crystals.core.data.server.tags.HCBlockTagProvider;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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

@Mod(HeartCrystals.MODID)
public class HeartCrystals {
    public static final String MODID = "heart_crystals";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> "1", "1"::equals, "1"::equals);

    public HeartCrystals() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        REGISTRY_HELPER.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::registerCapabilities);
        bus.addListener(this::dataSetup);

        context.registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PACKET_HANDLER.registerMessage(0, HCPlayerInfo.PlayerHealthInfoSync.class, HCPlayerInfo.PlayerHealthInfoSync::buffer, HCPlayerInfo.PlayerHealthInfoSync::new, HCPlayerInfo.PlayerHealthInfoSync::handler);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(HCPlayerInfo.PlayerHealthInfo.class);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        gen.addProvider(client, new HCLanguageProvider(event));

        gen.addProvider(server, new HCLootTableProvider(event));
        gen.addProvider(server, new HCBlockTagProvider(event));
        gen.addProvider(server, new HCRecipeProvider(event));
    }

}
