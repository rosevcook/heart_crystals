package com.rosemods.heart_crystals.core.other;

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

        if (player != null && !HCPlayerInfo.getPlayerHealthInfo(player).HealthSet) {
            executeHealthCommand(6, player);
            player.setHealth(6);
            player.getCapability(HCPlayerInfo.HEALTH_INFO_CAPABILITY, null).ifPresent((capability) -> {
                capability.HealthSet = true;
                capability.syncPlayerVariables(player);
            });
        }
        
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        syncPlayerInfo(event.getEntity());

        if (player != null)
            executeHealthCommand(HCPlayerInfo.getPlayerHealthInfo(player).HeartCount * 2, player);
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

        clone.HeartCount = original.HeartCount;
        clone.HealthSet = original.HealthSet;
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
            event.addCapability(new ResourceLocation(HeartCrystals.MODID, "player_info"), new HCPlayerInfo.PlayerVariablesProvider());
    }

    public static void executeHealthCommand(int health, Player player) { // prob a better way of doing this lol
        if (!player.level.isClientSide() && player.getServer() != null)
            player.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.position(), player.getRotationVector(), player.level instanceof ServerLevel serverLevel ? serverLevel: null, 4, player.getName().getString(), player.getDisplayName(), player.level.getServer(), player), "execute as @p run attribute @s minecraft:generic.max_health base set " + health);
    }

    private static void syncPlayerInfo(Player player) {
        if (player != null && !player.level.isClientSide())
            HCPlayerInfo.getPlayerHealthInfo(player).syncPlayerVariables(player);
    }

}
