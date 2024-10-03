package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.ModFluids;
import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(AetherTags.Blocks.ALLOWED_BUCKET_PICKUP).
                add(ModFluids.DIRTY_WATER_BLOCK.get());
    }
}