package me.jddev0.epta.integeration.emi;

import com.aetherteam.aether.block.AetherBlocks;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import me.jddev0.epta.recipe.AetherFarmlandCraftingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Optional;

@EmiEntrypoint
public class EnergizedPowerTAEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        registerRecipes(registry);
    }

    private void registerRecipes(EmiRegistry registry) {
        RecipeManager recipeManager = registry.getRecipeManager();

        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = recipeManager.getAllRecipesFor(RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof AetherFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(AetherBlocks.AETHER_FARMLAND),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(AetherBlocks.AETHER_DIRT)
                    }));

            registry.addRecipe(new EmiCraftingRecipe(
                    recipe.getIngredients().stream().map(EmiIngredient::of).toList(),
                    EmiStack.of(recipe.getResultItem(null)),
                    recipeOptional.get().id()
            ));
        }
    }
}
