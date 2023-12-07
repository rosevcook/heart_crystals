package com.rosemods.heart_crystals.core.data.server;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.*;

public class HCRecipeProvider extends RecipeProvider {
    public HCRecipeProvider(GatherDataEvent event) {
        super(event.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(HEART_LANTERN.get()).define('#', HEART_CRYSTAL.get()).define('I', Items.IRON_NUGGET).pattern("III").pattern("I#I").pattern("III").unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())).save(consumer, getSaveLocation(HEART_LANTERN));
        ShapedRecipeBuilder.shaped(HEART_CRYSTAL.get()).define('#', HEART_CRYSTAL_SHARD.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(consumer, getSaveLocation(HEART_CRYSTAL));
        ShapelessRecipeBuilder.shapeless(HEART_CRYSTAL_SHARD.get(), 9).requires(HEART_CRYSTAL.get()).unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())).save(consumer, getSaveLocation(HEART_CRYSTAL_SHARD));
        ShapelessRecipeBuilder.shapeless(HEART_BANNER_PATTERN.get()).requires(Items.PAPER).requires(HEART_CRYSTAL_SHARD.get()).unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(consumer, getSaveLocation(HEART_BANNER_PATTERN));
    }

    private static ResourceLocation getSaveLocation(RegistryObject<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem());
    }

}
