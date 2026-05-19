package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.CUPIDS_ARROW;
import static com.rosemods.heart_crystals.core.registry.HCItems.HEART_BANNER_PATTERN;

public class HCRecipeProvider extends BlueprintRecipeProvider {
    public HCRecipeProvider(GatherDataEvent event, HCDatapackProvider dataPack) {
        super(HeartCrystals.MOD_ID, event.getGenerator().getPackOutput(), dataPack.getRegistryProvider());
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HEART_CRYSTAL.get()).define('#', HEART_CRYSTAL_SHARD.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_CRYSTAL_SHARD.get(), 9).requires(HEART_CRYSTAL.get()).unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_BANNER_PATTERN.get()).requires(Items.PAPER).requires(HEART_CRYSTAL_SHARD.get()).unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CUPIDS_ARROW.get(), 6).define('#', HEART_CRYSTAL_SHARD.get()).define('S', Items.STICK).define('F', Items.FEATHER).pattern("#").pattern("S").pattern("F").unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(output);
        conditionalRecipe(output, new TagEmptyCondition(ResourceLocation.fromNamespaceAndPath("c", "ingots/silver")), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN.get()).define('#', HEART_CRYSTAL.get()).define('I', Items.IRON_INGOT).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())));
        conditionalRecipe(output, new NotCondition(new TagEmptyCondition(ResourceLocation.fromNamespaceAndPath("c", "ingots/silver"))), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN.get()).define('#', HEART_CRYSTAL.get()).define('I', TagUtil.itemTag("c", "ingots/silver")).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())), HeartCrystals.location("heart_lantern_from_silver"));
    }

}
