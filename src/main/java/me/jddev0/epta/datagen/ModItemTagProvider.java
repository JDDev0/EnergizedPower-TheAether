package me.jddev0.epta.datagen;

import me.jddev0.ep.registry.tags.CommonItemTags;
import me.jddev0.epta.item.EPTAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        getOrCreateTagBuilder(CommonItemTags.TOOLS_HAMMERS).
                add(EPTAItems.SKYROOT_HAMMER).
                add(EPTAItems.HOLYSTONE_HAMMER).
                add(EPTAItems.ZANITE_HAMMER).
                add(EPTAItems.GRAVITITE_HAMMER);
    }
}
