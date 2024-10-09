package me.jddev0.epta.datagen;

import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.item.EPTAItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBasicModels();
    }

    private void registerBasicModels() {
        basicItem(EPTAItems.SKYROOT_HAMMER);
        basicItem(EPTAItems.HOLYSTONE_HAMMER);
        basicItem(EPTAItems.ZANITE_HAMMER);
        basicItem(EPTAItems.GRAVITITE_HAMMER);

        basicItem(EPTAItems.SKYROOT_DIRTY_WATER_BUCKET);
    }

    private ItemModelBuilder basicItem(RegistryObject<? extends Item> item) {
        ResourceLocation itemID = item.getId();

        return withExistingParent(itemID.getPath(), "generated")
                .texture("layer0", new ResourceLocation(itemID.getNamespace(), "item/" + itemID.getPath()));
    }
}
