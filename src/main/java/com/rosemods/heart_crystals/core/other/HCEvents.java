package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HeartCrystals.MOD_ID)
public class HCEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(player);

        if (player != null) {
            HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
            int minimum = HCConfig.COMMON.minimum.get();

            if (!info.healthSet) {
                setMaxHealthAttribute(minimum * 2, player);
                player.setHealth(minimum * 2);
                info.healthSet = true;
                info.syncHealthInfo(player);
            } else if (info.heartCount < HCConfig.COMMON.minimum.get()) {
                setMaxHealthAttribute(minimum * 2, player);
                player.setHealth(minimum * 2);
                info.heartCount = minimum;
                info.syncHealthInfo(player);
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(event.getEntity());

        if (player != null) {
            HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
            setMaxHealthAttribute(info.heartCount * 2, player);
            player.setHealth(info.heartCount * 2);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncPlayerInfo(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().revive();
        HCPlayerInfo.PlayerHealthInfo original = HCPlayerInfo.getPlayerHealthInfo(event.getOriginal());
        HCPlayerInfo.PlayerHealthInfo clone = HCPlayerInfo.getPlayerHealthInfo(event.getEntity());

        clone.heartCount = original.heartCount;
        clone.healthSet = original.healthSet;
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
            event.addCapability(HeartCrystals.REGISTRY_HELPER.prefix("player_info"), new HCPlayerInfo.PlayerHealthInfoProvider());
    }

    public static void setMaxHealthAttribute(int health, Player player) {
        if (!player.level.isClientSide() && player.getServer() != null) {
            AttributeInstance attribute = player.getAttributes().getInstance(Attributes.MAX_HEALTH);

            if (attribute != null)
                attribute.setBaseValue(health);
        }

    }

    private static void syncPlayerInfo(Player player) {
        if (player != null && !player.level.isClientSide())
            HCPlayerInfo.getPlayerHealthInfo(player).syncHealthInfo(player);
    }

}
