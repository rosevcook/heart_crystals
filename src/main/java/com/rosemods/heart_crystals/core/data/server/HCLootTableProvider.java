package com.rosemods.heart_crystals.core.data.server;

import com.google.common.collect.ImmutableList;
import com.rosemods.heart_crystals.core.HeartCrystals;
import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HCLootTableProvider extends LootTableProvider {

    public HCLootTableProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), BuiltInLootTables.all(), ImmutableList.of(new LootTableProvider.SubProviderEntry(HCBlockLoot::new, LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }

    private static <T> Iterable<T> getContent(IForgeRegistry<T> entry) {
        return entry.getValues().stream().filter(i -> entry.getKey(i) != null
                && HeartCrystals.MOD_ID.equals(entry.getKey(i).getNamespace())).collect(Collectors.toSet());
    }

    private static class HCBlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

        protected HCBlockLoot() {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            this.dropSelf(HCBlocks.HEART_CRYSTAL.get());
            this.dropSelf(HCBlocks.HEART_CRYSTAL_SHARD.get());
            this.dropSelf(HCBlocks.HEART_LANTERN.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return getContent(ForgeRegistries.BLOCKS);
        }
    }

}
