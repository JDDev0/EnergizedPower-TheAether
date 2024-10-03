package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.ModFluids;
import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(AetherTags.Fluids.ALLOWED_BUCKET_PICKUP).
                add(ModFluids.DIRTY_WATER.get(),
                        ModFluids.FLOWING_DIRTY_WATER.get());
    }
}
