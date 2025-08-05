package com.rosemods.heart_crystals.core.other;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rosemods.heart_crystals.core.HCConfig;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCAttachments;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.function.UnaryOperator;

public class HCPlayerInfo {

    public static PlayerHealthInfo getPlayerHealthInfo(Entity entity) {
        return entity.getData(HCAttachments.HEALTH_INFO);
    }

    private static PlayerHealthInfo modifyPlayerHealthInfo(Player entity, UnaryOperator<PlayerHealthInfo> modifier) {
        var modified = modifier.apply(getPlayerHealthInfo(entity));
        entity.setData(HCAttachments.HEALTH_INFO, modified);
        modified.syncHealthInfo(entity);
        return modified;
    }

    public static HCPlayerInfo.PlayerHealthInfo setHeartSet(Player player, boolean set) {
        return HCPlayerInfo.modifyPlayerHealthInfo(player, it -> new HCPlayerInfo.PlayerHealthInfo(it.heartCount(), set));
    }

    public static HCPlayerInfo.PlayerHealthInfo setHeartCount(Player player, int count) {
        return HCPlayerInfo.modifyPlayerHealthInfo(player, it -> new HCPlayerInfo.PlayerHealthInfo(count, it.healthSet()));
    }

    public record PlayerHealthInfo(int heartCount, boolean healthSet) implements ICapabilityProvider<Player, Void, PlayerHealthInfo> {

        public static final Codec<PlayerHealthInfo> CODEC = RecordCodecBuilder.create(builder ->
                builder.group(
                        Codec.INT.fieldOf("count").forGetter(PlayerHealthInfo::heartCount),
                        Codec.BOOL.fieldOf("set").forGetter(PlayerHealthInfo::healthSet)
                ).apply(builder, PlayerHealthInfo::new)
        );

        public static final StreamCodec<FriendlyByteBuf, PlayerHealthInfo> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT, PlayerHealthInfo::heartCount,
                ByteBufCodecs.BOOL, PlayerHealthInfo::healthSet,
                PlayerHealthInfo::new
        );

        public PlayerHealthInfo() {
            this(HCConfig.COMMON.minimum.get(), false);
        }

        public void syncHealthInfo(Player player) {
            if (player instanceof ServerPlayer serverPlayer)
                PacketDistributor.sendToPlayer(serverPlayer, new PlayerHealthInfoSync(this));
        }

        @Override
        public PlayerHealthInfo getCapability(Player player, Void context) {
            return this;
        }

    }

    public record PlayerHealthInfoSync(PlayerHealthInfo info) implements CustomPacketPayload {
        public static final TypeAndCodec<FriendlyByteBuf, PlayerHealthInfoSync> TYPE = new TypeAndCodec<>(
                new Type<>(HeartCrystals.location("health_info_sync")),
                PlayerHealthInfo.STREAM_CODEC.map(PlayerHealthInfoSync::new, PlayerHealthInfoSync::info)
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE.type();
        }

    }

}
