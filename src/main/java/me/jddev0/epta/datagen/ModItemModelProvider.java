package me.jddev0.epta.datagen;

import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.item.EPTAItems;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

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

    private ItemModelBuilder basicItem(Holder<Item> item) {
        ResourceLocation itemID = Objects.requireNonNull(item.unwrapKey().orElseThrow()).location();

        return withExistingParent(itemID.getPath(), "generated")
                .texture("layer0", new ResourceLocation(itemID.getNamespace(), "item/" + itemID.getPath()));
    }
}
