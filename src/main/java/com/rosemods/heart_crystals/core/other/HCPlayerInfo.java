package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;


public class HCPlayerInfo {
    public static final Capability<PlayerHealthInfo> HEALTH_INFO_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    public static PlayerHealthInfo getPlayerHealthInfo(Entity entity) {
        return entity.getCapability(HCPlayerInfo.HEALTH_INFO_CAPABILITY,null).orElse(new PlayerHealthInfo());
    }

    public static class PlayerHealthInfo {
        public int heartCount;
        public boolean healthSet;

        public PlayerHealthInfo() {
            this.heartCount = HCConfig.COMMON.minimum.get();
            this.healthSet = false;
        }

        public void syncHealthInfo(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                HeartCrystals.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerHealthInfoSync(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("PlayerHeartsCount", this.heartCount);
            nbt.putBoolean("PlayerBaseHealthSet", this.healthSet);

            return nbt;
        }

        public void readNBT(Tag Tag) {
            CompoundTag nbt = (CompoundTag)Tag;
            this.heartCount = nbt.getInt("PlayerHeartsCount");
            this.healthSet = nbt.getBoolean("PlayerBaseHealthSet");
        }
    }

    public static class PlayerHealthInfoSync {
        private final PlayerHealthInfo info;

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
                    info.heartCount = sync.getHealthInfo().heartCount;
                    info.healthSet = sync.getHealthInfo().healthSet;
                }
            });

            context.setPacketHandled(true);
        }

    }

    public static class PlayerHealthInfoProvider implements ICapabilitySerializable<Tag> {
        private final PlayerHealthInfo info = new PlayerHealthInfo();
        private final LazyOptional<PlayerHealthInfo> instance = LazyOptional.of(() -> this.info);

        @Override
        public<T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == HCPlayerInfo.HEALTH_INFO_CAPABILITY ? this.instance.cast() : LazyOptional.empty();
        }

        @Override
        public Tag serializeNBT() {
            return this.info.writeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt) {
            this.info.readNBT(nbt);
        }

    }

}
