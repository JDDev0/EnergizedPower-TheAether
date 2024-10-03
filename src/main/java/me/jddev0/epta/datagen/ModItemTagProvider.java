package me.jddev0.epta.datagen;

import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epta.EnergizedPowerTAMod;
import me.jddev0.epta.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagLookup, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(CommonItemTags.TOOLS_HAMMERS).
                add(ModItems.SKYROOT_HAMMER.get()).
                add(ModItems.HOLYSTONE_HAMMER.get()).
                add(ModItems.ZANITE_HAMMER.get()).
                add(ModItems.GRAVITITE_HAMMER.get());
    }
}
