package me.jddev0.epta.datagen;

import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnergizedPowerTAMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnergizedPowerTADataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));

        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(generator, blockTagProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModFluidTagProvider(generator, existingFileHelper));
    }
}
