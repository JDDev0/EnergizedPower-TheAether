package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.EPFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagProvider<Fluid> {
    public ModFluidTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> lookupProvider) {
        super(output, RegistryKeys.FLUID, lookupProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookupProvider) {
        getOrCreateTagBuilder(AetherTags.Fluids.ALLOWED_BUCKET_PICKUP).
                add(EPFluids.DIRTY_WATER,
                        EPFluids.FLOWING_DIRTY_WATER);
    }
}
