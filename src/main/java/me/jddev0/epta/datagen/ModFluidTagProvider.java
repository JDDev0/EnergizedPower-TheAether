package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.ModFluids;
import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(DataGenerator output, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(AetherTags.Fluids.ALLOWED_BUCKET_PICKUP).
                add(ModFluids.DIRTY_WATER.get(),
                        ModFluids.FLOWING_DIRTY_WATER.get());
    }
}
