package com.rosemods.heart_crystals.core;

import com.rosemods.heart_crystals.client.particle.CupidsArrowParticle;
import com.rosemods.heart_crystals.core.data.client.*;
import com.rosemods.heart_crystals.core.data.server.HCDatapackProvider;
import com.rosemods.heart_crystals.core.data.server.HCLootTableProvider;
import com.rosemods.heart_crystals.core.data.server.HCRecipeProvider;
import com.rosemods.heart_crystals.core.data.server.tags.*;
import com.rosemods.heart_crystals.core.other.HCClientSync;
import com.rosemods.heart_crystals.core.other.HCPlayerInfo;
import com.rosemods.heart_crystals.core.registry.*;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(HeartCrystals.MOD_ID)
public class HeartCrystals {
    public static final String MOD_ID = "heart_crystals";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public HeartCrystals(IEventBus bus, ModContainer container) {
        HCBlocks.BLOCKS.register(bus);
        HCItems.ITEMS.register(bus);
        HCEntityTypes.ENTITY_TYPES.register(bus);
        HCBlockEntities.BLOCK_ENTITIES.register(bus);
        HCSoundEvents.SOUNDS.register(bus);
        HCFeatures.FEATURES.register(bus);
        HCParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::registerCapabilities);
        bus.addListener(this::dataSetup);
        bus.addListener(this::registerMessage);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            HCBlocks.setupTabEditors();
            HCItems.setupTabEditors();
            bus.addListener(this::registerSpriteSets);
        }

        container.registerConfig(ModConfig.Type.COMMON, HCConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //DataUtil.addMix(Potions.AWKWARD, HCBlocks.HEART_CRYSTAL_SHARD.get().asItem(), Potions.REGENERATION);
            DispenserBlock.registerProjectileBehavior(HCItems.CUPIDS_ARROW);
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(HCEntityTypes::registerClient);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        //event.register(HCPlayerInfo.PlayerHealthInfo.class);
    }

    private void registerSpriteSets(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(HCParticleTypes.CUPIDS_ARROW.get(), CupidsArrowParticle.Provider::new);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        boolean server = event.includeServer();
        HCBlockTagProvider blockTags;
        HCDatapackProvider dataPack;
        gen.addProvider(server, dataPack = new HCDatapackProvider(event));
        gen.addProvider(server, blockTags = new HCBlockTagProvider(event));
        gen.addProvider(server, new HCLootTableProvider(event, dataPack));
        gen.addProvider(server, new HCRecipeProvider(event, dataPack));
        gen.addProvider(server, new HCItemTagProvider(event, blockTags, dataPack));
        gen.addProvider(server, new HCBannerPatternTagProvider(event, dataPack));
        gen.addProvider(server, new HCPaintingVariantTagsProvider(event, dataPack));
        gen.addProvider(server, new HCTrimMaterialTagsProvider(event, dataPack));

        boolean client = event.includeClient();
        gen.addProvider(client, new HCLanguageProvider(event));
        gen.addProvider(client, new HCModelProvider(event));
        gen.addProvider(client, new HCSoundProvider(event));
        gen.addProvider(client, new HCSpriteSourceProvider(event, dataPack));
        gen.addProvider(client, new HCParticleProvider(event));
    }

    private void registerMessage(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");
        registrar.playToClient(
                HCPlayerInfo.PlayerHealthInfoSync.TYPE.type(),
                HCPlayerInfo.PlayerHealthInfoSync.TYPE.codec(),
                HCClientSync::receivePacket
        );
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
