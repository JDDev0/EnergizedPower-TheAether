package me.jddev0.epta.integration.jei;

import com.aetherteam.aether.block.AetherBlocks;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.recipe.AetherFarmlandCraftingRecipe;
import me.shedaniel.rei.plugincompatibilities.api.REIPluginCompatIgnore;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Arrays;
import java.util.Optional;

@JeiPlugin
@REIPluginCompatIgnore
public class EnergizedPowerTAJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        //Add aether farmland special crafting recipe if loaded
        Optional<RecipeHolder<CraftingRecipe>> recipeOptional = recipeManager.getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.CRAFTING).stream().
                filter(recipe -> recipe.value() instanceof AetherFarmlandCraftingRecipe).findFirst();
        if(recipeOptional.isPresent()) {
            ShapelessRecipe recipe = new ShapelessRecipe("", CraftingBookCategory.MISC, new ItemStack(AetherBlocks.AETHER_FARMLAND),
                    NonNullList.of(null, new Ingredient[] {
                            Ingredient.of(ItemTags.HOES),
                            Ingredient.of(AetherBlocks.AETHER_DIRT)
                    }));

            registration.addRecipes(RecipeTypes.CRAFTING, Arrays.asList(new RecipeHolder(recipeOptional.get().id(), recipe)));
        }
    }
}
