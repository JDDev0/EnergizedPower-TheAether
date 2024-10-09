package me.jddev0.epta.item;

import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether.item.combat.AetherItemTiers;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import me.jddev0.ep.fluid.EPFluids;
import me.jddev0.ep.item.HammerItem;
import me.jddev0.epta.EnergizedPowerTAMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EPTAItems {
    private EPTAItems() {}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EnergizedPowerTAMod.MODID);

    public static final RegistryObject<Item> SKYROOT_HAMMER = ITEMS.register("skyroot_hammer",
            () -> new HammerItem(AetherItemTiers.SKYROOT, new Item.Properties()));
    public static final RegistryObject<Item> HOLYSTONE_HAMMER = ITEMS.register("holystone_hammer",
            () -> new HammerItem(AetherItemTiers.HOLYSTONE, new Item.Properties()));
    public static final RegistryObject<Item> ZANITE_HAMMER = ITEMS.register("zanite_hammer",
            () -> new HammerItem(AetherItemTiers.ZANITE, new Item.Properties()));
    public static final RegistryObject<Item> GRAVITITE_HAMMER = ITEMS.register("gravitite_hammer",
            () -> new HammerItem(AetherItemTiers.GRAVITITE, new Item.Properties()));

    public static final RegistryObject<Item> SKYROOT_DIRTY_WATER_BUCKET = ITEMS.register("skyroot_dirty_water_bucket",
            () -> new SkyrootBucketItem(EPFluids.DIRTY_WATER, new Item.Properties().craftRemainder(AetherItems.SKYROOT_BUCKET.get()).stacksTo(1)));

    public static void setupBucketReplacements() {
        SkyrootBucketItem.REPLACEMENTS.put(EPFluids.DIRTY_WATER_BUCKET_ITEM, SKYROOT_DIRTY_WATER_BUCKET);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
