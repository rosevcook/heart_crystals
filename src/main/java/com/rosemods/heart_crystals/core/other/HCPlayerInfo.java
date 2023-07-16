package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class HCPlayerInfo {
    public static final Capability<PlayerHealthInfo> HEALTH_INFO_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    public static PlayerHealthInfo getPlayerHealthInfo(Entity entity) {
        return entity.getCapability(HCPlayerInfo.HEALTH_INFO_CAPABILITY,null).orElse(new PlayerHealthInfo());
    }

    public static class PlayerHealthInfo {
        public int HeartCount = HCConfig.COMMON.minimum.get();
        public boolean HealthSet = false;

        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                HeartCrystals.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerHealthInfoSync(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("PlayerHeartsCount", this.HeartCount);
            nbt.putBoolean("PlayerBaseHealthSet", this.HealthSet);

            return nbt;
        }

        public void readNBT(Tag Tag) {
            CompoundTag nbt = (CompoundTag)Tag;
            this.HeartCount = nbt.getInt("PlayerHeartsCount");
            this.HealthSet = nbt.getBoolean("PlayerBaseHealthSet");
        }
    }

    public static class PlayerHealthInfoSync {
        private PlayerHealthInfo info;

        public PlayerHealthInfoSync(PlayerHealthInfo info) {
            this.info = info;
        }

        public PlayerHealthInfoSync(FriendlyByteBuf buffer) {
            this.info = new PlayerHealthInfo();
            this.info.readNBT(buffer.readNbt());
        }

        public PlayerHealthInfo getHealthInfo() {
            return this.info;
        }

        public static void buffer(PlayerHealthInfoSync message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag)message.getHealthInfo().writeNBT());
        }

        public static void handler(PlayerHealthInfoSync sync, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();

            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer() && Minecraft.getInstance().player != null) {
                    PlayerHealthInfo info = getPlayerHealthInfo(Minecraft.getInstance().player);
                    info.HeartCount = sync.getHealthInfo().HeartCount;
                    info.HealthSet = sync.getHealthInfo().HealthSet;
                }
            });

            context.setPacketHandled(true);
        }

    }
}
