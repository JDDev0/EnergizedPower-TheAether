package me.jddev0.epta;

import com.mojang.logging.LogUtils;
import me.jddev0.ep.item.EPCreativeModeTab;
import me.jddev0.epta.item.EPTAItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(EnergizedPowerTAMod.MODID)
public class EnergizedPowerTAMod {
    public static final String MODID = "energizedpowerta";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnergizedPowerTAMod(IEventBus modEventBus) {
        EPTAItems.register(modEventBus);

        modEventBus.addListener(this::addCreativeTab);
        modEventBus.addListener(this::commonSetup);
    }

    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab() == EPCreativeModeTab.ENERGIZED_POWER_TAB.get()) {
            event.accept(EPTAItems.SKYROOT_HAMMER);
            event.accept(EPTAItems.HOLYSTONE_HAMMER);
            event.accept(EPTAItems.ZANITE_HAMMER);
            event.accept(EPTAItems.GRAVITITE_HAMMER);

            event.accept(EPTAItems.SKYROOT_DIRTY_WATER_BUCKET);
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        EPTAItems.setupBucketReplacements();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
