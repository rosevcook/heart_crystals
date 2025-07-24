package com.rosemods.heart_crystals.core.data.server;

import com.rosemods.heart_crystals.core.HeartCrystals;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

import static com.rosemods.heart_crystals.core.registry.HCBlocks.*;
import static com.rosemods.heart_crystals.core.registry.HCItems.CUPIDS_ARROW;
import static com.rosemods.heart_crystals.core.registry.HCItems.HEART_BANNER_PATTERN;

public class HCRecipeProvider extends BlueprintRecipeProvider {
    public HCRecipeProvider(GatherDataEvent event) {
        super(HeartCrystals.MOD_ID, event.getGenerator().getPackOutput());
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HEART_CRYSTAL.get()).define('#', HEART_CRYSTAL_SHARD.get()).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_CRYSTAL_SHARD.get(), 9).requires(HEART_CRYSTAL.get()).unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HEART_BANNER_PATTERN.get()).requires(Items.PAPER).requires(HEART_CRYSTAL_SHARD.get()).unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CUPIDS_ARROW.get(), 6).define('#', HEART_CRYSTAL_SHARD.get()).define('S', Items.STICK).define('F', Items.FEATHER).pattern("#").pattern("S").pattern("F").unlockedBy(getHasName(HEART_CRYSTAL_SHARD.get()), has(HEART_CRYSTAL_SHARD.get())).save(consumer);
        conditionalRecipe(consumer, new TagEmptyCondition(new ResourceLocation("forge", "ingots/silver")), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN.get()).define('#', HEART_CRYSTAL.get()).define('I', Items.IRON_INGOT).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())));
        conditionalRecipe(consumer, new NotCondition(new TagEmptyCondition(new ResourceLocation("forge", "ingots/silver"))), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HEART_LANTERN.get()).define('#', HEART_CRYSTAL.get()).define('I', TagUtil.itemTag("forge", "ingots/silver")).pattern("I").pattern("#").pattern("I").unlockedBy(getHasName(HEART_CRYSTAL.get()), has(HEART_CRYSTAL.get())), HeartCrystals.REGISTRY_HELPER.prefix("heart_lantern_from_silver"));
    }

}
