package me.jddev0.epta.datagen;

import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator output, ExistingFileHelper existingFileHelper) {
        super(output, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBasicModels();
    }

    private void registerBasicModels() {
        basicItem(ModItems.SKYROOT_HAMMER);
        basicItem(ModItems.HOLYSTONE_HAMMER);
        basicItem(ModItems.ZANITE_HAMMER);
        basicItem(ModItems.GRAVITITE_HAMMER);

        basicItem(ModItems.SKYROOT_DIRTY_WATER_BUCKET);
    }

    private ItemModelBuilder basicItem(RegistryObject<? extends Item> item) {
        ResourceLocation itemID = item.getId();

        return withExistingParent(itemID.getPath(), "generated")
                .texture("layer0", new ResourceLocation(itemID.getNamespace(), "item/" + itemID.getPath()));
    }
}
