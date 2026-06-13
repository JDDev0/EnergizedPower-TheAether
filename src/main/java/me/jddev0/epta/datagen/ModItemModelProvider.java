package me.jddev0.epta.datagen;

import me.jddev0.epta.item.EPTAItems;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemModelProvider {
    private final ItemModelGenerators generator;

    ModItemModelProvider(ItemModelGenerators generator) {
        this.generator = generator;
    }

    void registerItems() {
        registerBasicModels();
    }

    private void registerBasicModels() {
        basicItem(EPTAItems.SKYROOT_HAMMER);
        basicItem(EPTAItems.HOLYSTONE_HAMMER);
        basicItem(EPTAItems.ZANITE_HAMMER);
        basicItem(EPTAItems.GRAVITITE_HAMMER);

        basicItem(EPTAItems.SKYROOT_DIRTY_WATER_BUCKET);
    }

    private ResourceLocation basicItem(Item item) {
        generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);

        return ModelLocationUtils.getModelLocation(item);
    }
}
