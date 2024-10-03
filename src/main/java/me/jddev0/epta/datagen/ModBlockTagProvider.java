package me.jddev0.epta.datagen;

import com.aetherteam.aether.AetherTags;
import me.jddev0.ep.fluid.ModFluids;
import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(DataGenerator output, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, EnergizedPowerTAMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(AetherTags.Blocks.ALLOWED_BUCKET_PICKUP).
                add(ModFluids.DIRTY_WATER_BLOCK.get());
    }
}
