package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = HeartCrystals.MOD_ID)
public class HCEvents {

    @SubscribeEvent
    public static void onRegisterBrewing(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.AWKWARD, HCBlocks.HEART_CRYSTAL_SHARD.get().asItem(), Potions.REGENERATION);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(player);
        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
        int minimum = HCConfig.COMMON.minimum.get();

        if (!info.healthSet()) {
            setMaxHealthAttribute(minimum * 2, player);
            player.setHealth(minimum * 2);
            HCPlayerInfo.setHeartSet(player, true);
        } else if (info.heartCount() < HCConfig.COMMON.minimum.get()) {
            setMaxHealthAttribute(minimum * 2, player);
            player.setHealth(minimum * 2);
            HCPlayerInfo.setHeartCount(player, minimum);
        }

    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(event.getEntity());
        HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
        setMaxHealthAttribute(info.heartCount() * 2, player);
        player.setHealth(info.heartCount() * 2);
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncPlayerInfo(event.getEntity());
    }

    public static void setMaxHealthAttribute(int health, Player player) {
        if (!player.level().isClientSide() && player.getServer() != null) {
            AttributeInstance attribute = player.getAttributes().getInstance(Attributes.MAX_HEALTH);

            if (attribute != null)
                attribute.setBaseValue(health);
        }

    }

    private static void syncPlayerInfo(Player player) {
        if (player != null && !player.level().isClientSide())
            HCPlayerInfo.getPlayerHealthInfo(player).syncHealthInfo(player);
    }

}
