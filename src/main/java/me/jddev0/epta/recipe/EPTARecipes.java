package me.jddev0.epta.recipe;

import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class EPTARecipes {
    private EPTARecipes() {}

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, EnergizedPowerTAMod.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, EnergizedPowerTAMod.MODID);

    public static final Supplier<RecipeSerializer<AetherFarmlandCraftingRecipe>>
            AETHER_FARMLAND_CRAFTING_RECIPE_SERIALIZER = SERIALIZERS.register("aether_farmland_crafting",
            () -> new SimpleCraftingRecipeSerializer<>(AetherFarmlandCraftingRecipe::new));

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
        TYPES.register(modEventBus);
    }
}
