package me.jddev0.epta;

import com.aetherteam.aether.item.AetherItems;
import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.CreativeTabEntriesHelper;
import me.jddev0.ep.item.EPCreativeModeTab;
import me.jddev0.epta.item.EPTAItems;
import me.jddev0.epta.mixin.item.ItemRecipeRemainderSetter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;

import java.util.function.Consumer;

public class EnergizedPowerTAMod implements ModInitializer {
    public static final String MODID = "energizedpowerta";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean isEPATLoaded = false;
    public static boolean isAetherLoaded = false;

    @Override
    public void onInitialize() {
        isEPATLoaded = true;

        EPTAItems.register();

        addCreativeTab();

        EPTAItems.setupBucketReplacements();

        onSkyrootDirtyWaterBucketSetRecipeRemainderHook();
    }

    private void addCreativeTab() {
        addCreativeTabFor(EPCreativeModeTab.ENERGIZED_POWER_TAB_REG_KEY, event -> {
            event.accept(EPTAItems.SKYROOT_HAMMER);
            event.accept(EPTAItems.HOLYSTONE_HAMMER);
            event.accept(EPTAItems.ZANITE_HAMMER);
            event.accept(EPTAItems.GRAVITITE_HAMMER);

            event.accept(EPTAItems.SKYROOT_DIRTY_WATER_BUCKET);
        });
    }

    private void addCreativeTabFor(RegistryKey<ItemGroup> groupKey,
                                   Consumer<CreativeTabEntriesHelper> consumer) {
        ItemGroupEvents.modifyEntriesEvent(groupKey).
                register(entries -> consumer.accept(new CreativeTabEntriesHelper(entries)));
    }

    public static void onSkyrootDirtyWaterBucketSetRecipeRemainderHook() {
        if(isEPATLoaded && isAetherLoaded) {
            ((ItemRecipeRemainderSetter)EPTAItems.SKYROOT_DIRTY_WATER_BUCKET).setRecipeRemainder(AetherItems.SKYROOT_BUCKET.get());
        }
    }
}
