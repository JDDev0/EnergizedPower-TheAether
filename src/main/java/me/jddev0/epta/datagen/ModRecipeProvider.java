package me.jddev0.epta.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import me.jddev0.ep.datagen.recipe.CrusherFinishedRecipe;
import me.jddev0.ep.datagen.recipe.PlantGrowthChamberFinishedRecipe;
import me.jddev0.ep.datagen.recipe.PulverizerFinishedRecipe;
import me.jddev0.ep.datagen.recipe.SawmillFinishedRecipe;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.ep.recipe.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final String THE_AETHER_MOD_ID = Aether.MODID;
    private static final String PATH_PREFIX = "compat/" + THE_AETHER_MOD_ID + "/";

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        buildCrusherRecipes(output);
        buildPulverizerRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCrusherRecipes(RecipeOutput output) {
        addCrusherRecipe(output, Ingredient.of(AetherBlocks.HOLYSTONE_BRICKS.get()), new ItemStack(AetherBlocks.HOLYSTONE.get()),
                "holystone_bricks");
    }

    private void buildPulverizerRecipes(RecipeOutput output) {
        addPulverizerRecipe(output, Ingredient.of(AetherBlocks.AMBROSIUM_ORE.get()),
                new PulverizerRecipe.OutputItemStackWithPercentages(new ItemStack(AetherItems.AMBROSIUM_SHARD.get()), new double[] {
                        1., .67, .17
                }, new double[] {
                        1., .67, .33, .17
                }), "ambrosium_ores");

        addPulverizerRecipe(output, Ingredient.of(AetherBlocks.ZANITE_ORE.get()),
                new PulverizerRecipe.OutputItemStackWithPercentages(new ItemStack(AetherItems.ZANITE_GEMSTONE.get()), new double[] {
                        1., .67, .17
                }, new double[] {
                        1., .67, .33, .17
                }), "zanite_ores");
    }

    private void buildSawmillRecipes(RecipeOutput output) {
        addBasicWoodSawmillRecipe(output, new ItemStack(AetherBlocks.SKYROOT_PLANKS.get()),
                Ingredient.of(AetherTags.Items.CRAFTS_SKYROOT_PLANKS), Ingredient.of(AetherBlocks.SKYROOT_FENCE.get()),
                Ingredient.of(AetherBlocks.SKYROOT_FENCE_GATE.get()), Ingredient.of(AetherBlocks.SKYROOT_DOOR.get()),
                Ingredient.of(AetherBlocks.SKYROOT_TRAPDOOR.get()), Ingredient.of(AetherBlocks.SKYROOT_PRESSURE_PLATE.get()),
                Ingredient.of(AetherBlocks.SKYROOT_SIGN.get()), Ingredient.of(AetherItems.SKYROOT_BOAT.get()),
                Ingredient.of(AetherItems.SKYROOT_CHEST_BOAT.get()),
                false, "skyroot");

        addSawmillRecipe(output, Ingredient.of(AetherBlocks.SKYROOT_BOOKSHELF.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 6),
                new ItemStack(Items.BOOK, 3), "skyroot_planks", "skyroot_bookshelf");

        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_BUCKET.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                3, "skyroot_planks", "skyroot_bucket");

        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_SWORD.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                1, "skyroot_planks", "skyroot_sword");
        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_SHOVEL.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get()),
                2, "skyroot_planks", "skyroot_shovel");
        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_PICKAXE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 3),
                2, "skyroot_planks", "skyroot_pickaxe");
        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_AXE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 3),
                2, "skyroot_planks", "skyroot_axe");
        addSawmillRecipe(output, Ingredient.of(AetherItems.SKYROOT_HOE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                2, "skyroot_planks", "skyroot_hoe");

        addSawmillRecipe(output, Ingredient.of(AetherBlocks.SKYROOT_PLANKS.get()), new ItemStack(AetherItems.SKYROOT_STICK.get(), 3),
                1, "skyroot_sticks", "skyroot_planks");
    }

    private void buildPlantGrowthChamberRecipes(RecipeOutput output) {
        addBasicFlowerGrowingRecipe(output, AetherBlocks.PURPLE_FLOWER.get(), "purple_flower");
        addBasicFlowerGrowingRecipe(output, AetherBlocks.WHITE_FLOWER.get(), "white_flower");
    }

    private void addCrusherRecipe(RecipeOutput recipeOutput, Ingredient input, ItemStack output, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crusher/" +
                getItemName(output.getItem()) + "_from_crushing_" + recipeIngredientName);

        CrusherFinishedRecipe recipe = new CrusherFinishedRecipe(
                recipeId,
                output, input
        );
        recipeOutput.accept(recipe);
    }

    private static void addPulverizerRecipe(RecipeOutput recipeOutput, Ingredient input,
                                            PulverizerRecipe.OutputItemStackWithPercentages output,
                                            String recipeIngredientName) {
        addPulverizerRecipe(recipeOutput, input, output,
                new PulverizerRecipe.OutputItemStackWithPercentages(ItemStack.EMPTY, new double[0], new double[0]), recipeIngredientName);
    }
    private static void addPulverizerRecipe(RecipeOutput recipeOutput, Ingredient input,
                                            PulverizerRecipe.OutputItemStackWithPercentages output,
                                            PulverizerRecipe.OutputItemStackWithPercentages secondaryOutput,
                                            String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "pulverizer/" +
                getItemName(output.output().getItem()) + "_from_pulverizer_" + recipeIngredientName);

        PulverizerFinishedRecipe recipe = new PulverizerFinishedRecipe(
                recipeId,
                output, secondaryOutput, input
        );
        recipeOutput.accept(recipe);
    }

    private void addBasicWoodSawmillRecipe(RecipeOutput recipeOutput, ItemStack planksItem,
                                                  Ingredient logsInput, Ingredient fenceInput, Ingredient fenceGateInput,
                                                  Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                  Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                  boolean isRaft, String woodName) {
        addSawmillRecipe(recipeOutput, logsInput, planksItem.copyWithCount(6), 1, getItemName(planksItem.getItem()),
                woodName + "_logs");

        addBasicWoodWithoutLogsSawmillRecipe(recipeOutput, planksItem, fenceInput, fenceGateInput, doorInput, trapdoorInput,
                pressurePlateInput, signInput, boatInput, chestBoatInput, isRaft, woodName);
    }
    private void addBasicWoodWithoutLogsSawmillRecipe(RecipeOutput recipeOutput, ItemStack planksItem,
                                                             Ingredient fenceInput, Ingredient fenceGateInput,
                                                             Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                             Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                             boolean isRaft, String woodName) {
        addBasicWoodWithoutLogsAndBoatsSawmillRecipe(recipeOutput, planksItem, fenceInput, fenceGateInput, doorInput,
                trapdoorInput, pressurePlateInput, signInput, woodName);

        addSawmillRecipe(recipeOutput, boatInput, planksItem.copyWithCount(4), 3, getItemName(planksItem.getItem()),
                woodName + (isRaft?"_raft":"_boat"));
        addSawmillRecipe(recipeOutput, chestBoatInput, planksItem.copyWithCount(5), 7, getItemName(planksItem.getItem()),
                woodName + (isRaft?"_chest_raft":"_chest_boat"));
    }
    private void addBasicWoodWithoutLogsAndBoatsSawmillRecipe(RecipeOutput recipeOutput, ItemStack planksItem,
                                                                     Ingredient fenceInput, Ingredient fenceGateInput,
                                                                     Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                                     Ingredient signInput, String woodName) {
        addSawmillRecipe(recipeOutput, fenceInput, planksItem, 2, getItemName(planksItem.getItem()),
                woodName + "_fence");
        addSawmillRecipe(recipeOutput, fenceGateInput, planksItem.copyWithCount(2), 3, getItemName(planksItem.getItem()),
                woodName + "_fence_gate");
        addSawmillRecipe(recipeOutput, doorInput, planksItem, 3, getItemName(planksItem.getItem()),
                woodName + "_door");
        addSawmillRecipe(recipeOutput, trapdoorInput, planksItem.copyWithCount(2), 3, getItemName(planksItem.getItem()),
                woodName + "_trapdoor");
        addSawmillRecipe(recipeOutput, pressurePlateInput, planksItem, 2, getItemName(planksItem.getItem()),
                woodName + "_pressure_plate");
        addSawmillRecipe(recipeOutput, signInput, planksItem.copyWithCount(2), 1, getItemName(planksItem.getItem()),
                woodName + "_sign");
    }
    private void addSawmillRecipe(RecipeOutput recipeOutput, Ingredient input, ItemStack output,
                                         int sawdustAmount, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillFinishedRecipe recipe = new SawmillFinishedRecipe(
                recipeId,
                output, input, sawdustAmount
        );
        recipeOutput.accept(recipe);
    }
    private static void addSawmillRecipe(RecipeOutput recipeOutput, Ingredient input, ItemStack output,
                                         ItemStack secondaryOutput, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillFinishedRecipe recipe = new SawmillFinishedRecipe(
                recipeId,
                output, secondaryOutput, input
        );
        recipeOutput.accept(recipe);
    }

    private void addBasicFlowerGrowingRecipe(RecipeOutput recipeOutput, ItemLike flowerItem,
                                                    String outputName) {
        addPlantGrowthChamberRecipe(recipeOutput, Ingredient.of(flowerItem), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(flowerItem), new double[] {
                        1., 1., .33
                })
        }, 16000, outputName, getItemName(flowerItem));
    }
    private void addPlantGrowthChamberRecipe(RecipeOutput recipeOutput, Ingredient input,
                                             OutputItemStackWithPercentages[] outputs, int ticks,
                                             String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberFinishedRecipe recipe = new PlantGrowthChamberFinishedRecipe(
                recipeId,
                outputs, input, ticks
        );
        recipeOutput.accept(recipe);
    }
}
