package me.jddev0.epta.recipe;

import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public final class EPTARecipes {
    private EPTARecipes() {}

    public static final RecipeSerializer<AetherFarmlandCraftingRecipe>
            AETHER_FARMLAND_CRAFTING_RECIPE_SERIALIZER = createSerializer("aether_farmland_crafting",
            new SimpleCraftingRecipeSerializer<>(AetherFarmlandCraftingRecipe::new));

    private static <T extends Recipe<?>> RecipeSerializer<T> createSerializer(String name, RecipeSerializer<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, name), instance);
    }
    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name, RecipeType<T> instance) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, name), instance);
    }
    public static void register() {

    }
}
