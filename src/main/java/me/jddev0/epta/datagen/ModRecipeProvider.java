package me.jddev0.epta.datagen;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherItems;
import me.jddev0.ep.block.EPBlocks;
import me.jddev0.ep.datagen.recipe.*;
import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.ep.util.ItemStackUtils;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.ep.recipe.*;
import me.jddev0.epta.item.EPTAItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final String THE_AETHER_MOD_ID = Aether.MODID;
    private static final String PATH_PREFIX = "compat/" + THE_AETHER_MOD_ID + "/";

    public ModRecipeProvider(DataGenerator output) {
        super(output);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> output) {
        buildCraftingTableRecipes(output);
        buildCrusherRecipes(output);
        buildPulverizerRecipes(output);
        buildSawmillRecipes(output);
        buildPlantGrowthChamberRecipes(output);
    }

    private void buildCraftingTableRecipes(Consumer<FinishedRecipe> output) {
        buildToolsCraftingRecipes(output);

        buildItemTransportCraftingRecipes(output);
    }
    private void buildToolsCraftingRecipes(Consumer<FinishedRecipe> output) {
        addHammerCraftingRecipe(output, AetherTags.Items.SKYROOT_TOOL_CRAFTING, EPTAItems.SKYROOT_HAMMER.get());
        addHammerCraftingRecipe(output, AetherBlocks.HOLYSTONE.get(), EPTAItems.HOLYSTONE_HAMMER.get());
        addHammerCraftingRecipe(output, AetherTags.Items.GEMS_ZANITE, EPTAItems.ZANITE_HAMMER.get());
        addHammerCraftingRecipe(output, AetherTags.Items.PROCESSED_GRAVITITE, EPTAItems.GRAVITITE_HAMMER.get());
    }
    private void buildItemTransportCraftingRecipes(Consumer<FinishedRecipe> output) {
        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'c', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_ITEM.get()),
                'H', Ingredient.of(Items.HOPPER)
        ), new String[] {
                "CCC",
                "CHC",
                "CcC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get()));

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(Tags.Items.STORAGE_BLOCKS_IRON),
                'R', Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get())
        ), new String[] {
                "CRC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SORTER_ITEM.get()));

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'l', Ingredient.of(Items.LEVER),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get())
        ), new String[] {
                "ClC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SWITCH_ITEM.get()));

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get())
        ), new String[] {
                "CIC",
                "ILI",
                "CRC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_SPLITTER_ITEM.get()));

        addShapedCraftingRecipe(output, has(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM), Map.of(
                'C', Ingredient.of(AetherBlocks.HOLYSTONE.get()),
                'I', Ingredient.of(CommonItemTags.PLATES_IRON),
                'R', Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                'L', Ingredient.of(EPBlocks.ITEM_CONVEYOR_BELT_LOADER_ITEM.get())
        ), new String[] {
                "CRC",
                "ILI",
                "CIC"
        }, new ItemStack(EPBlocks.ITEM_CONVEYOR_BELT_MERGER_ITEM.get()));
    }

    private void buildCrusherRecipes(Consumer<FinishedRecipe> output) {
        addCrusherRecipe(output, Ingredient.of(AetherBlocks.HOLYSTONE_BRICKS.get()), new ItemStack(AetherBlocks.HOLYSTONE.get()),
                "holystone_bricks");
    }

    private void buildPulverizerRecipes(Consumer<FinishedRecipe> output) {
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

    private void buildSawmillRecipes(Consumer<FinishedRecipe> output) {
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

    private void buildPlantGrowthChamberRecipes(Consumer<FinishedRecipe> output) {
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

    private static void addHammerCraftingRecipe(Consumer<FinishedRecipe> output, ItemLike materialInput, ItemLike hammerItem) {
        addShapedCraftingRecipe(output, has(materialInput), Map.of(
                'S', Ingredient.of(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.of(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem));
    }
    private static void addHammerCraftingRecipe(Consumer<FinishedRecipe> output, TagKey<Item> materialInput, ItemLike hammerItem) {
        addShapedCraftingRecipe(output, has(materialInput), Map.of(
                'S', Ingredient.of(AetherTags.Items.SKYROOT_STICKS),
                'M', Ingredient.of(materialInput)
        ), new String[] {
                " M ",
                " SM",
                "S  "
        }, new ItemStack(hammerItem));
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern, ItemStack result) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern, ItemStack result, String group) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, group, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern, ItemStack result, String group, String recipeIdSuffix) {
        addShapedCraftingRecipe(output, hasIngredientTrigger, key, pattern, result, group, recipeIdSuffix, "");
    }
    private static void addShapedCraftingRecipe(Consumer<FinishedRecipe> output, InventoryChangeTrigger.TriggerInstance hasIngredientTrigger,
                                                Map<Character, Ingredient> key, String[] pattern, ItemStack result, String group, String recipeIdSuffix,
                                                String recipeIdPrefix) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crafting/" +
                recipeIdPrefix + getItemName(result.getItem()) + recipeIdSuffix);

        Advancement.Builder advancementBuilder = Advancement.Builder.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .addCriterion("has_the_ingredient", hasIngredientTrigger)
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);
        ShapedFinishedRecipe recipe = new ShapedFinishedRecipe(
                recipeId,
                Objects.requireNonNullElse(group, ""),
                key, pattern, result,
                advancementBuilder,
                withPrefix(recipeId, "recipes/")
        );
        output.accept(recipe);
    }

    private void addCrusherRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input, ItemStack output, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "crusher/" +
                getItemName(output.getItem()) + "_from_crushing_" + recipeIngredientName);

        CrusherFinishedRecipe recipe = new CrusherFinishedRecipe(
                recipeId,
                output, input
        );
        recipeOutput.accept(recipe);
    }

    private static void addPulverizerRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input,
                                            PulverizerRecipe.OutputItemStackWithPercentages output,
                                            String recipeIngredientName) {
        addPulverizerRecipe(recipeOutput, input, output,
                new PulverizerRecipe.OutputItemStackWithPercentages(ItemStack.EMPTY, new double[0], new double[0]), recipeIngredientName);
    }
    private static void addPulverizerRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input,
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

    private void addBasicWoodSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, ItemStack planksItem,
                                                  Ingredient logsInput, Ingredient fenceInput, Ingredient fenceGateInput,
                                                  Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                  Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                  boolean isRaft, String woodName) {
        addSawmillRecipe(recipeOutput, logsInput, ItemStackUtils.copyWithCount(planksItem, 6), 1, getItemName(planksItem.getItem()),
                woodName + "_logs");

        addBasicWoodWithoutLogsSawmillRecipe(recipeOutput, planksItem, fenceInput, fenceGateInput, doorInput, trapdoorInput,
                pressurePlateInput, signInput, boatInput, chestBoatInput, isRaft, woodName);
    }
    private void addBasicWoodWithoutLogsSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, ItemStack planksItem,
                                                             Ingredient fenceInput, Ingredient fenceGateInput,
                                                             Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                             Ingredient signInput, Ingredient boatInput, Ingredient chestBoatInput,
                                                             boolean isRaft, String woodName) {
        addBasicWoodWithoutLogsAndBoatsSawmillRecipe(recipeOutput, planksItem, fenceInput, fenceGateInput, doorInput,
                trapdoorInput, pressurePlateInput, signInput, woodName);

        addSawmillRecipe(recipeOutput, boatInput, ItemStackUtils.copyWithCount(planksItem, 4), 3, getItemName(planksItem.getItem()),
                woodName + (isRaft?"_raft":"_boat"));
        addSawmillRecipe(recipeOutput, chestBoatInput, ItemStackUtils.copyWithCount(planksItem, 5), 7, getItemName(planksItem.getItem()),
                woodName + (isRaft?"_chest_raft":"_chest_boat"));
    }
    private void addBasicWoodWithoutLogsAndBoatsSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, ItemStack planksItem,
                                                                     Ingredient fenceInput, Ingredient fenceGateInput,
                                                                     Ingredient doorInput, Ingredient trapdoorInput, Ingredient pressurePlateInput,
                                                                     Ingredient signInput, String woodName) {
        addSawmillRecipe(recipeOutput, fenceInput, planksItem, 2, getItemName(planksItem.getItem()),
                woodName + "_fence");
        addSawmillRecipe(recipeOutput, fenceGateInput, ItemStackUtils.copyWithCount(planksItem, 2), 3, getItemName(planksItem.getItem()),
                woodName + "_fence_gate");
        addSawmillRecipe(recipeOutput, doorInput, planksItem, 3, getItemName(planksItem.getItem()),
                woodName + "_door");
        addSawmillRecipe(recipeOutput, trapdoorInput, ItemStackUtils.copyWithCount(planksItem, 2), 3, getItemName(planksItem.getItem()),
                woodName + "_trapdoor");
        addSawmillRecipe(recipeOutput, pressurePlateInput, planksItem, 2, getItemName(planksItem.getItem()),
                woodName + "_pressure_plate");
        addSawmillRecipe(recipeOutput, signInput, ItemStackUtils.copyWithCount(planksItem, 2), 1, getItemName(planksItem.getItem()),
                woodName + "_sign");
    }
    private void addSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input, ItemStack output,
                                         int sawdustAmount, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillFinishedRecipe recipe = new SawmillFinishedRecipe(
                recipeId,
                output, input, sawdustAmount
        );
        recipeOutput.accept(recipe);
    }
    private static void addSawmillRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input, ItemStack output,
                                         ItemStack secondaryOutput, String outputName, String recipeIngredientName) {
        ResourceLocation recipeId = new ResourceLocation(EnergizedPowerTAMod.MODID, PATH_PREFIX + "sawmill/" +
                outputName + "_from_sawing_" + recipeIngredientName);

        SawmillFinishedRecipe recipe = new SawmillFinishedRecipe(
                recipeId,
                output, secondaryOutput, input
        );
        recipeOutput.accept(recipe);
    }

    private void addBasicFlowerGrowingRecipe(Consumer<FinishedRecipe> recipeOutput, ItemLike flowerItem,
                                                    String outputName) {
        addPlantGrowthChamberRecipe(recipeOutput, Ingredient.of(flowerItem), new OutputItemStackWithPercentages[] {
                new OutputItemStackWithPercentages(new ItemStack(flowerItem), new double[] {
                        1., 1., .33
                })
        }, 16000, outputName, getItemName(flowerItem));
    }
    private void addPlantGrowthChamberRecipe(Consumer<FinishedRecipe> recipeOutput, Ingredient input,
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

    public static ResourceLocation withPrefix(ResourceLocation resourceLocation, String pathPrefix) {
        return new ResourceLocation(resourceLocation.getNamespace(), pathPrefix + resourceLocation.getPath());
    }

    private static InventoryChangeTrigger.TriggerInstance has(RegistryObject<Item> item) {
        return has(item.get());
    }
}
