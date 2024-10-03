package me.jddev0.epta;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.ModCreativeModeTab;
import me.jddev0.epta.item.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EnergizedPowerTAMod.MODID)
public class EnergizedPowerTAMod {
    public static final String MODID = "energizedpowerta";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnergizedPowerTAMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        modEventBus.addListener(this::addCreativeTab);
        modEventBus.addListener(this::commonSetup);
    }

    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == ModCreativeModeTab.ENERGIZED_POWER_TAB.get()) {
            event.accept(ModItems.SKYROOT_HAMMER);
            event.accept(ModItems.HOLYSTONE_HAMMER);
            event.accept(ModItems.ZANITE_HAMMER);
            event.accept(ModItems.GRAVITITE_HAMMER);

            event.accept(ModItems.SKYROOT_DIRTY_WATER_BUCKET);
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        ModItems.setupBucketReplacements();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
