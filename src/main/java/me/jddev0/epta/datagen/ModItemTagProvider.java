package me.jddev0.epta.datagen;

import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(DataGenerator output, BlockTagsProvider blockTagsProvider,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, blockTagsProvider, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(CommonItemTags.TOOLS_HAMMERS).
                add(ModItems.SKYROOT_HAMMER.get()).
                add(ModItems.HOLYSTONE_HAMMER.get()).
                add(ModItems.ZANITE_HAMMER.get()).
                add(ModItems.GRAVITITE_HAMMER.get());
    }
}
