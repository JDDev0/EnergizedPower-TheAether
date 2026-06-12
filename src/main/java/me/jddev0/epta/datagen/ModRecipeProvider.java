package me.jddev0.epta.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import me.jddev0.ep.api.EPAPI;
import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.ep.soil.EPSoilTypeTags;
import me.jddev0.ep.soil.SoilType;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.ep.recipe.*;
import me.jddev0.epta.item.EPTAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final String THE_AETHER_MOD_ID = Aether.MODID;
    private static final String PATH_PREFIX = "compat/" + THE_AETHER_MOD_ID + "/";

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    public void generate(RecipeExporter output) {
        buildCraftingRecipes(output);
        buildCrusherRecipes(output);
        buildPulverizerRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCraftingRecipes(RecipeExporter output) {
        buildToolsCraftingRecipes(output);

        buildItemTransportCraftingRecipes(output);
    }
    private void buildToolsCraftingRecipes(RecipeExporter output) {
        addHammerCraftingRecipe(output, AetherTags.Items.SKYROOT_TOOL_CRAFTING, EPTAItems.SKYROOT_HAMMER);
        addHammerCraftingRecipe(output, AetherBlocks.HOLYSTONE.get(), EPTAItems.HOLYSTONE_HAMMER);
        addHammerCraftingRecipe(output, AetherTags.Items.GEMS_ZANITE, EPTAItems.ZANITE_HAMMER);
        addHammerCraftingRecipe(output, AetherTags.Items.PROCESSED_GRAVITITE, EPTAItems.GRAVITITE_HAMMER);
    }
    private void buildItemTransportCraftingRecipes(RecipeExporter output) {
        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_ITEM), Map.of(
                'C', Ingredient.ofItems(AetherBlocks.HOLYSTONE.get()),
                'c', Ingredient.ofItems(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_ITEM),
                'H', Ingredient.ofItems(Items.HOPPER)
        ), new String[] {
                "CCC",
                "CHC",
                "CcC"
        }, new ItemStack(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.ofItems(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.fromTag(ConventionalItemTags.STORAGE_BLOCKS_IRON),
                'R', Ingredient.fromTag(ConventionalItemTags.STORAGE_BLOCKS_REDSTONE),
                'L', Ingredient.ofItems(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CRC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_SORTER_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.ofItems(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.fromTag(CommonItemTags.PLATES_IRON),
                'R', Ingredient.fromTag(ConventionalItemTags.REDSTONE_DUSTS),
                'l', Ingredient.ofItems(Items.LEVER),
                'L', Ingredient.ofItems(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "ClC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_SWITCH_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.ofItems(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.fromTag(CommonItemTags.PLATES_IRON),
                'R', Ingredient.fromTag(ConventionalItemTags.REDSTONE_DUSTS),
                'L', Ingredient.ofItems(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CIC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_SPLITTER_ITEM), CraftingRecipeCategory.MISC);

        addShapedCraftingRecipe(output, conditionsFromItem(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.ofItems(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.fromTag(CommonItemTags.PLATES_IRON),
                'R', Ingredient.fromTag(ConventionalItemTags.REDSTONE_DUSTS),
                'L', Ingredient.ofItems(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_LOADER_ITEM)
        ), new String[] {
                "CRC",
                "ILI",
                "CIC"
        }, new ItemStack(EPBlocks.BASIC_ITEM_CONVEYOR_BELT_MERGER_ITEM), CraftingRecipeCategory.MISC);
    }

    private void buildCrusherRecipes(RecipeExporter output) {
        addCrusherRecipe(output, Ingredient.ofItems(AetherBlocks.HOLYSTONE_BRICKS.get()), new ItemStack(AetherBlocks.HOLYSTONE.get()),
                "holystone_bricks");
    }

    private void buildPulverizerRecipes(RecipeExporter output) {
        addPulverizerRecipe(output, Ingredient.ofItems(AetherBlocks.AMBROSIUM_ORE.get()),
                new PulverizerRecipe.OutputItemStackWithPercentages(new ItemStack(AetherItems.AMBROSIUM_SHARD.get()), new double[] {
                        1., .67, .17
                }, new double[] {
                        1., .67, .33, .17
                }), "ambrosium_ores");

        addPulverizerRecipe(output, Ingredient.ofItems(AetherBlocks.ZANITE_ORE.get()),
                new PulverizerRecipe.OutputItemStackWithPercentages(new ItemStack(AetherItems.ZANITE_GEMSTONE.get()), new double[] {
                        1., .67, .17
                }, new double[] {
                        1., .67, .33, .17
                }), "zanite_ores");
    }

    private void buildSawmillRecipes(RecipeExporter output) {
        addBasicWoodSawmillRecipe(output, new ItemStack(AetherBlocks.SKYROOT_PLANKS.get()),
                Ingredient.fromTag(AetherTags.Items.CRAFTS_SKYROOT_PLANKS), Ingredient.ofItems(AetherBlocks.SKYROOT_FENCE.get()),
                Ingredient.ofItems(AetherBlocks.SKYROOT_FENCE_GATE.get()), Ingredient.ofItems(AetherBlocks.SKYROOT_DOOR.get()),
                Ingredient.ofItems(AetherBlocks.SKYROOT_TRAPDOOR.get()), Ingredient.ofItems(AetherBlocks.SKYROOT_PRESSURE_PLATE.get()),
                Ingredient.ofItems(AetherBlocks.SKYROOT_SIGN.get()), Ingredient.ofItems(AetherItems.SKYROOT_BOAT.get()),
                Ingredient.ofItems(AetherItems.SKYROOT_CHEST_BOAT.get()),
                false, "skyroot");

        addSawmillRecipe(output, Ingredient.ofItems(AetherBlocks.SKYROOT_BOOKSHELF.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 6),
                new ItemStack(Items.BOOK, 3), "skyroot_planks", "skyroot_bookshelf");

        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_BUCKET.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                3, "skyroot_planks", "skyroot_bucket");

        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_SWORD.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                1, "skyroot_planks", "skyroot_sword");
        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_SHOVEL.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get()),
                2, "skyroot_planks", "skyroot_shovel");
        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_PICKAXE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 3),
                2, "skyroot_planks", "skyroot_pickaxe");
        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_AXE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 3),
                2, "skyroot_planks", "skyroot_axe");
        addSawmillRecipe(output, Ingredient.ofItems(AetherItems.SKYROOT_HOE.get()), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                2, "skyroot_planks", "skyroot_hoe");
        addSawmillRecipe(output, Ingredient.ofItems(EPTAItems.SKYROOT_HAMMER), new ItemStack(AetherBlocks.SKYROOT_PLANKS.get(), 2),
                2, "skyroot_planks", "skyroot_hammer");

        addSawmillRecipe(output, Ingredient.ofItems(AetherBlocks.SKYROOT_PLANKS.get()), new ItemStack(AetherItems.SKYROOT_STICK.get(), 3),
                1, "skyroot_sticks", "skyroot_planks");
    }

    private void buildPlantGrowthChamberRecipes(RecipeExporter output) {
        addBasicFlowerGrowingRecipe(output, AetherBlocks.PURPLE_FLOWER.get(), "purple_flower");
        addBasicFlowerGrowingRecipe(output, AetherBlocks.WHITE_FLOWER.get(), "white_flower");

        addPlantGrowthChamberRecipe(output, Ingredient.ofItems(AetherBlocks.BERRY_BUSH_STEM.get()), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(AetherBlocks.BERRY_BUSH_STEM.get()), new double[] {
                        1.
                }),
                new OutputItemStackWithPercentages(new ItemStack(AetherItems.BLUE_BERRY.get()), new double[] {
                        1., .75, .25
                })
        }, EPSoilTypeTags.CROPS, Fluids.WATER, 0.0625, 4000, "blue_berry", "berry_bush_stem");
    }

    private static void addHammerCraftingRecipe(RecipeExporter output, ItemConvertible materialInput, ItemConvertible hammerItem) {
        addShapedCraftingRecipe(output, conditionsFromItem(materialInput), Map.of(
                'S', Ingredient.fromTag(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.ofItems(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem), CraftingRecipeCategory.MISC);
    }
    private static void addHammerCraftingRecipe(RecipeExporter output, TagKey<Item> materialInput, ItemConvertible hammerItem) {
        addShapedCraftingRecipe(output, conditionsFromTag(materialInput), Map.of(
                'S', Ingredient.fromTag(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.fromTag(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem), CraftingRecipeCategory.MISC);
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, category, group, recipeIdSuffix, "");
    }
    private static void addShapedCraftingRecipe(RecipeExporter output, AdvancementCriterion<InventoryChangedCriterion.Conditions> hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern,
                                                ItemStack result, CraftingRecipeCategory category,
                                                String group, String recipeIdSuffix, String recipeIdPrefix) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemPath(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = output.getAdvancementBuilder()
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .criterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        ShapedRecipe recipe = new ShapedRecipe(
                Objects.requireNonNullElse(group, ""),
                category, RawShapedRecipe.create(key, pattern), result
        );
        output.accept(recipeId, recipe, advancementBuilder.build(recipeId.withPrefixedPath("recipes/")));
    }

    private void addCrusherRecipe(RecipeExporter RecipeExporter, Ingredient input, ItemStack output, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crusher/" +
                getItemPath(output.getItem()) + "_from_crushing_" + recipeIngredientName);

        CrusherRecipe recipe = new CrusherRecipe(
                output, input
        );
        RecipeExporter.accept(recipeId, recipe, null);
    }

    private static void addPulverizerRecipe(RecipeExporter RecipeExporter, Ingredient input,
                                            PulverizerRecipe.OutputItemStackWithPercentages output,
                                            String recipeIngredientName) {
        addPulverizerRecipe(RecipeExporter, input, output,
                new PulverizerRecipe.OutputItemStackWithPercentages(ItemStack.EMPTY, new double[0], new double[0]), recipeIngredientName);
    }
    private static void addPulverizerRecipe(RecipeExporter RecipeExporter, Ingredient input,
                                            PulverizerRecipe.OutputItemStackWithPercentages output,
                                            PulverizerRecipe.OutputItemStackWithPercentages secondaryOutput,
                                            String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "pulverizer/" +
                getItemPath(output.output().getItem()) + "_from_pulverizer_" + recipeIngredientName);

        PulverizerRecipe recipe = new PulverizerRecipe(
                output, secondaryOutput, input
        );
        RecipeExporter.accept(recipeId, recipe, null);
    }

    private void addBasicWoodSawmillRecipe(RecipeExporter RecipeExporter, ItemStack planksItem,
                                                  Ingredient logsInput, Ingredient fenceInput, Ingredient fenceGateInput,
                                                  Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                  Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                  boolean isRaft, String woodName) {
        addSawmillRecipe(RecipeExporter, logsInput, planksItem.copyWithCount(6), 1, getItemPath(planksItem.getItem()),
                woodName + "_logs");

        addBasicWoodWithoutLogsSawmillRecipe(RecipeExporter, planksItem, fenceInput, fenceGateInput, doorInput, trapdoorInput,
                pressurePlateInput, signInput, boatInput, chestBoatInput, isRaft, woodName);
    }
    private void addBasicWoodWithoutLogsSawmillRecipe(RecipeExporter RecipeExporter, ItemStack planksItem,
                                                             Ingredient fenceInput, Ingredient fenceGateInput,
                                                             Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                             Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                             boolean isRaft, String woodName) {
        addBasicWoodWithoutLogsAndBoatsSawmillRecipe(RecipeExporter, planksItem, fenceInput, fenceGateInput, doorInput,
                trapdoorInput, pressurePlateInput, signInput, woodName);

        addSawmillRecipe(RecipeExporter, boatInput, planksItem.copyWithCount(4), 3, getItemPath(planksItem.getItem()),
                woodName + (isRaft?"_raft":"_boat"));
        addSawmillRecipe(RecipeExporter, chestBoatInput, planksItem.copyWithCount(5), 7, getItemPath(planksItem.getItem()),
                woodName + (isRaft?"_chest_raft":"_chest_boat"));
    }
    private void addBasicWoodWithoutLogsAndBoatsSawmillRecipe(RecipeExporter RecipeExporter, ItemStack planksItem,
                                                                     Ingredient fenceInput, Ingredient fenceGateInput,
                                                                     Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                                     Ingredient signInput, String woodName) {
        addSawmillRecipe(RecipeExporter, fenceInput, planksItem, 2, getItemPath(planksItem.getItem()),
                woodName + "_fence");
        addSawmillRecipe(RecipeExporter, fenceGateInput, planksItem.copyWithCount(2), 3, getItemPath(planksItem.getItem()),
                woodName + "_fence_gate");
        addSawmillRecipe(RecipeExporter, doorInput, planksItem, 3, getItemPath(planksItem.getItem()),
                woodName + "_door");
        addSawmillRecipe(RecipeExporter, trapdoorInput, planksItem.copyWithCount(2), 3, getItemPath(planksItem.getItem()),
                woodName + "_trapdoor");
        addSawmillRecipe(RecipeExporter, pressurePlateInput, planksItem, 2, getItemPath(planksItem.getItem()),
                woodName + "_pressure_plate");
        addSawmillRecipe(RecipeExporter, signInput, planksItem.copyWithCount(2), 1, getItemPath(planksItem.getItem()),
                woodName + "_sign");
    }
    private void addSawmillRecipe(RecipeExporter RecipeExporter, Ingredient input, ItemStack output,
                                         int sawdustAmount, String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(
                output, input, sawdustAmount
        );
        RecipeExporter.accept(recipeId, recipe, null);
    }
    private static void addSawmillRecipe(RecipeExporter RecipeExporter, Ingredient input, ItemStack output,
                                         ItemStack secondaryOutput, String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillRecipe recipe = new SawmillRecipe(
                output, secondaryOutput, input
        );
        RecipeExporter.accept(recipeId, recipe, null);
    }

    private void addBasicFlowerGrowingRecipe(RecipeExporter recipeExporter, ItemConvertible flowerItem, String outputName) {
        addPlantGrowthChamberRecipe(recipeExporter, Ingredient.ofItems(flowerItem), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(flowerItem), new double[] {
                        1., 1., .33
                })
        }, EPSoilTypeTags.FLOWERS, Fluids.WATER, 0.0625, 4000, outputName, getItemPath(flowerItem));
    }
    private void addPlantGrowthChamberRecipe(RecipeExporter recipeExporter, Ingredient input,
                                             OutputItemStackWithPercentages[] outputs,
                                             TagKey<SoilType> soilType,
                                             Fluid fluid, double fluidConsumption, int ticks,
                                             String outputName, String recipeIngredientName) {
        addPlantGrowthChamberRecipe(recipeExporter, input, outputs, soilType, new Fluid[] {fluid}, fluidConsumption, ticks, outputName, recipeIngredientName);
    }
    private void addPlantGrowthChamberRecipe(RecipeExporter recipeExporter, Ingredient input,
                                             OutputItemStackWithPercentages[] outputs,
                                             TagKey<SoilType> soilType,
                                             Fluid[] fluid, double fluidConsumption, int ticks,
                                             String outputName, String recipeIngredientName) {
        Identifier recipeId = Identifier.of(EnergizedPowerTAMod.MODID, PATH_PREFIX + "growing/" +
                outputName + "_from_growing_" + recipeIngredientName);

        PlantGrowthChamberRecipe recipe = new PlantGrowthChamberRecipe(outputs, input, soilType, fluid, fluidConsumption, ticks);
        recipeExporter.accept(recipeId, recipe, null);
    }
}
