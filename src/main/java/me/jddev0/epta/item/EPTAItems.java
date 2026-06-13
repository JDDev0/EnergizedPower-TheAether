package me.jddev0.epta.item;

import com.aetherteam.aether.item.combat.AetherItemTiers;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import me.jddev0.ep.fluid.EPFluids;
import me.jddev0.ep.item.HammerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class EPTAItems {
    private EPTAItems() {}
    
    public static final Item SKYROOT_HAMMER = registerItem("skyroot_hammer",
            new HammerItem(AetherItemTiers.SKYROOT, new Item.Properties()));
    public static final Item HOLYSTONE_HAMMER = registerItem("holystone_hammer",
            new HammerItem(AetherItemTiers.HOLYSTONE, new Item.Properties()));
    public static final Item ZANITE_HAMMER = registerItem("zanite_hammer",
            new HammerItem(AetherItemTiers.ZANITE, new Item.Properties()));
    public static final Item GRAVITITE_HAMMER = registerItem("gravitite_hammer",
            new HammerItem(AetherItemTiers.GRAVITITE, new Item.Properties()));

    public static final Item SKYROOT_DIRTY_WATER_BUCKET = registerItem("skyroot_dirty_water_bucket",
            new SkyrootBucketItem(EPFluids.DIRTY_WATER, new Item.Properties().stacksTo(1)));

    public static void setupBucketReplacements() {
        SkyrootBucketItem.REPLACEMENTS.put(() -> EPFluids.DIRTY_WATER_BUCKET_ITEM, () -> SKYROOT_DIRTY_WATER_BUCKET);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath("energizedpowerta", name), item);
    }

    public static void register() {

    }
}
