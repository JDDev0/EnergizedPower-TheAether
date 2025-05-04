package me.jddev0.epta.datagen;

import me.jddev0.epta.item.EPTAItems;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModItemModelProvider {
    private final ItemModelGenerator generator;

    ModItemModelProvider(ItemModelGenerator generator) {
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

    private Identifier basicItem(Item item) {
        generator.register(item, Models.GENERATED);

        return ModelIds.getItemModelId(item);
    }
}
