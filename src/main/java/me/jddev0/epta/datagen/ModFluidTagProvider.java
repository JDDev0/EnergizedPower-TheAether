package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.EPFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider<Fluid> {
    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.FLUID, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        getOrCreateTagBuilder(AetherTags.Fluids.ALLOWED_BUCKET_PICKUP).
                add(EPFluids.DIRTY_WATER,
                        EPFluids.FLOWING_DIRTY_WATER);
    }
}
