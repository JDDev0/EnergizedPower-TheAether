package me.jddev0.epta.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.ep.recipe.*;
import me.jddev0.epta.item.EPTAItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final String THE_AETHER_MOD_ID = Aether.MODID;
    private static final String PATH_PREFIX = "compat/" + THE_AETHER_MOD_ID + "/";

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        buildCraftingRecipes(output);
        buildCrusherRecipes(output);
        buildPulverizerRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCraftingRecipes(RecipeOutput output) {
        buildToolsCraftingRecipes(output);

        buildItemTransportCraftingRecipes(output);
    }
    private void buildToolsCraftingRecipes(RecipeOutput output) {
        addHammerCraftingRecipe(output, AetherTags.Items.SKYROOT_TOOL_CRAFTING, EPTAItems.SKYROOT_HAMMER);
        addHammerCraftingRecipe(output, AetherBlocks.HOLYSTONE.get(), EPTAItems.HOLYSTONE_HAMMER);
        addHammerCraftingRecipe(output, AetherTags.Items.GEMS_ZANITE, EPTAItems.ZANITE_HAMMER);
        addHammerCraftingRecipe(output, AetherTags.Items.PROCESSED_GRAVITITE, EPTAItems.GRAVITITE_HAMMER);
    }
    private void buildItemTransportCraftingRecipes(RecipeOutput output) {
        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'c', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_ITEM),
                'H', Ingredient.of(Items.HOPPER)
        ), new String[] {
                "CCC",
                "CHC",
                "CcC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get()), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON),
                'R', Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CRC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SORTER_ITEM.get()), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'l', Ingredient.of(Items.LEVER),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "ClC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SWITCH_ITEM.get()), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CIC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SPLITTER_ITEM.get()), CraftingBookCategory.MISC);

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CRC",
                "ILI",
                "CIC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_MERGER_ITEM.get()), CraftingBookCategory.MISC);
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
        addSawmillRecipe(output, Ingredient.of(EPTAItems.SKYROOT_HAMMER.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                2, "skyroot_planks", "skyroot_hammer");

        addSawmillRecipe(output, Ingredient.of(AetherBlocks.SKYROOT_PLANKS.get()), new ItemStack(AetherItems.SKYROOT_STICK.get(), 3),
                1, "skyroot_sticks", "skyroot_planks");
    }

    private void buildPlantGrowthChamberRecipes(RecipeOutput output) {
        addBasicFlowerGrowingRecipe(output, AetherBlocks.PURPLE_FLOWER.get(), "purple_flower");
        addBasicFlowerGrowingRecipe(output, AetherBlocks.WHITE_FLOWER.get(), "white_flower");

        addPlantGrowthChamberRecipe(output, Ingredient.of(AetherBlocks.BERRY_BUSH_STEM.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(AetherBlocks.BERRY_BUSH_STEM.get()), new double[] {
                        1.
                }),
                new OutputItemStackWithPercentages(new ItemStack(AetherItems.BLUE_BERRY.get()), new double[] {
                        1., .75, .25
                })
        }, 16000, "blue_berry", "berry_bush_stem");
    }

    private static void addHammerCraftingRecipe(RecipeOutput output, ItemLike materialInput, ItemLike hammerItem) {
        addShapedCraftingRecipe(output, has(materialInput), Map.of(
                'S', Ingredient.of(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.of(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem), CraftingBookCategory.MISC);
    }
    private static void addHammerCraftingRecipe(RecipeOutput output, TagKey<Item> materialInput, ItemLike hammerItem) {
        addShapedCraftingRecipe(output, has(materialInput), Map.of(
                'S', Ingredient.of(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.of(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem), CraftingBookCategory.MISC);
    }
    private static void addShapedCraftingRecipe(RecipeOutput output, Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, "");
    }
    private static void addShapedCraftingRecipe(RecipeOutput output, Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private static void addShapedCraftingRecipe(RecipeOutput output, Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapedCraftingRecipe(RecipeOutput output, Criterion<InventoryChangeTrigger.TriggerInstance> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingBookCategory category,
                                                String group, String recipeIdSuffix, String recipeIdPrefix) {
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        ShapedRecipe recipe = new ShapedRecipe(
                Objects.requireNonNullElse(group, ""),
                category, ShapedRecipePattern.of(key, pattern), result
        );
        output.accept(recipeId, recipe, advancementBuilder.build(recipeId.withPrefix("recipes/")));
    }

    private void addCrusherRecipe(RecipeOutput recipeOutput, Ingredient input, ItemStack output, String recipeIngredientName) {
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crusher/" +
                getItemName(output.getItem()) + "_from_crushing_" + recipeIngredientName);

        CrusherRecipe recipe = new CrusherRecipe(
                output, input
        );
        recipeOutput.accept(recipeId, recipe, null);
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
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "pulverizer/" +
                getItemName(output.output().getItem()) + "_from_pulverizer_" + recipeIngredientName);

        PulverizerRecipe recipe = new PulverizerRecipe(
                output, secondaryOutput, input
        );
        recipeOutput.accept(recipeId, recipe, null);
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
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(
                output, input, sawdustAmount
        );
        recipeOutput.accept(recipeId, recipe, null);
    }
    private static void addSawmillRecipe(RecipeOutput recipeOutput, Ingredient input, ItemStack output,
                                         ItemStack secondaryOutput, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(
                output, secondaryOutput, input
        );
        recipeOutput.accept(recipeId, recipe, null);
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
        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(EnergizedPowerTAMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberRecipe recipe = new PlantGrowthChamberRecipe(
                outputs, input, ticks
        );
        recipeOutput.accept(recipeId, recipe, null);
    }
}
