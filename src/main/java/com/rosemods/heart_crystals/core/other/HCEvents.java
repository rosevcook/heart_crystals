package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HeartCrystals.MODID)
public class HCEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(player);

        if (player != null) {
            HCPlayerInfo.PlayerHealthInfo info = HCPlayerInfo.getPlayerHealthInfo(player);
            int minimum = HCConfig.COMMON.minimum.get();

            if (!info.healthSet) {
                executeHealthCommand(minimum * 2, player);
                player.setHealth(minimum * 2);
                info.healthSet = true;
                info.syncHealthInfo(player);
            } else if (info.heartCount < HCConfig.COMMON.minimum.get()) {
                executeHealthCommand(minimum * 2, player);
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
            executeHealthCommand(info.heartCount * 2, player);
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
            event.addCapability(new ResourceLocation(HeartCrystals.MODID, "player_info"), new HCPlayerInfo.PlayerHealthInfoProvider());
    }

    public static void executeHealthCommand(int health, Player player) { // prob a better way of doing this lol
        if (!player.level.isClientSide() && player.getServer() != null)
            player.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.position(), player.getRotationVector(),
                    player.level instanceof ServerLevel serverLevel ? serverLevel: null, 4, player.getName().getString(), player.getDisplayName(),
                    player.level.getServer(), player), "execute as @p run attribute @s minecraft:generic.max_health base set " + health);
    }

    private static void syncPlayerInfo(Player player) {
        if (player != null && !player.level.isClientSide())
            HCPlayerInfo.getPlayerHealthInfo(player).syncHealthInfo(player);
    }

}
