package me.jddev0.epta.integration.rei;

import com.aetherteam.aether.block.AetherBlocks;
import me.jddev0.epta.recipe.AetherFarmlandCraftingRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Optional;

public class EnergizedPowerTAREIPlugin implements REIClientPlugin {
    @Override
    public String getPluginProviderName() {
        return "EnergizedPower TA";
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = registry.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof AetherFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(AetherBlocks.AETHER_FARMLAND),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(AetherBlocks.AETHER_DIRT)
                    }));

            registry.add(DefaultCraftingDisplay.of(new RecipeHolder(recipeOptional.get().id(), recipe)));
        }
    }
}
