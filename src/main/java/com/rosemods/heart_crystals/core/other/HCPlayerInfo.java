package com.rosemods.heart_crystals.core.other;

import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.network.PacketDistributor;

public class HCPlayerInfo {
    public static final EntityCapability<PlayerHealthInfo, Void> HEALTH_INFO_CAPABILITY = EntityCapability.createVoid(HeartCrystals.location("health_info"), PlayerHealthInfo.class);

    public static PlayerHealthInfo getPlayerHealthInfo(Entity entity) {
        PlayerHealthInfo result = entity.getCapability(HEALTH_INFO_CAPABILITY, null);
        return result != null ? result : new PlayerHealthInfo();
    }

    public static class PlayerHealthInfo implements ICapabilityProvider<Player, Void, PlayerHealthInfo> {
        public int heartCount;
        public boolean healthSet;

        public PlayerHealthInfo() {
            this.heartCount = HCConfig.COMMON.minimum.get();
            this.healthSet = false;
        }

        public void syncHealthInfo(Player player) {
            if (player instanceof ServerPlayer serverPlayer)
                PacketDistributor.sendToPlayer(serverPlayer, new PlayerHealthInfoSync(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("PlayerHeartsCount", this.heartCount);
            nbt.putBoolean("PlayerBaseHealthSet", this.healthSet);

            return nbt;
        }

        public void readNBT(Tag Tag) {
            CompoundTag nbt = (CompoundTag) Tag;
            this.heartCount = nbt.getInt("PlayerHeartsCount");
            this.healthSet = nbt.getBoolean("PlayerBaseHealthSet");
        }

        @Override
        public PlayerHealthInfo getCapability(Player player, Void context) {
            return this;
        }

    }

    public static class PlayerHealthInfoSync implements CustomPacketPayload {
        public static final TypeAndCodec<FriendlyByteBuf, PlayerHealthInfoSync> TYPE = new TypeAndCodec<>(
                new Type<>(HeartCrystals.location("health_info_sync")),
                StreamCodec.of(
                        PlayerHealthInfoSync::encode,
                        PlayerHealthInfoSync::new
                )
        );

        private final PlayerHealthInfo info;

        public PlayerHealthInfoSync(PlayerHealthInfo info) {
            this.info = info;
        }

        private PlayerHealthInfoSync(FriendlyByteBuf buffer) {
            this.info = new PlayerHealthInfo();
            this.info.readNBT(buffer.readNbt());
        }

        public PlayerHealthInfo getHealthInfo() {
            return this.info;
        }

        public static void encode(FriendlyByteBuf buffer, PlayerHealthInfoSync message) {
            buffer.writeNbt(message.getHealthInfo().writeNBT());
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE.type();
        }

    }

}
