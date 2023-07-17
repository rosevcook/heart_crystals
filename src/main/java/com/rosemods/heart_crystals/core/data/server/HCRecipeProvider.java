package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.registry.HCBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class HCRecipeProvider extends RecipeProvider {
    public HCRecipeProvider(GatherDataEvent event) {
        super(event.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(HCBlocks.HEART_LANTERN.get()).define('#', HCBlocks.HEART_CRYSTAL.get()).define('I', Items.IRON_NUGGET).pattern("III").pattern("I#I").pattern("III").unlockedBy(getHasName(HCBlocks.HEART_CRYSTAL.get()), has(HCBlocks.HEART_CRYSTAL.get())).save(consumer, getSaveLocation(HCBlocks.HEART_LANTERN));
    }

    private static ResourceLocation getSaveLocation(RegistryObject<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem());
    }

}
