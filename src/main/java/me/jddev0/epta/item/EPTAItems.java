package me.jddev0.epta.item;

import com.aetherteam.aether.item.combat.AetherItemTiers;
import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import me.jddev0.ep.fluid.EPFluids;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class EPTAItems {
    private EPTAItems() {}
    
    public static final Item SKYROOT_HAMMER = registerItem("skyroot_hammer",
            new HammerItemFix(AetherItemTiers.SKYROOT, new Item.Settings()));
    public static final Item HOLYSTONE_HAMMER = registerItem("holystone_hammer",
            new HammerItemFix(AetherItemTiers.HOLYSTONE, new Item.Settings()));
    public static final Item ZANITE_HAMMER = registerItem("zanite_hammer",
            new HammerItemFix(AetherItemTiers.ZANITE, new Item.Settings()));
    public static final Item GRAVITITE_HAMMER = registerItem("gravitite_hammer",
            new HammerItemFix(AetherItemTiers.GRAVITITE, new Item.Settings()));

    public static final Item SKYROOT_DIRTY_WATER_BUCKET = registerItem("skyroot_dirty_water_bucket",
            new SkyrootBucketItem(EPFluids.DIRTY_WATER, new Item.Settings().maxCount(1)));

    public static void setupBucketReplacements() {
        SkyrootBucketItem.REPLACEMENTS.put(() -> EPFluids.DIRTY_WATER_BUCKET_ITEM, () -> SKYROOT_DIRTY_WATER_BUCKET);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of("energizedpowerta", name), item);
    }

    public static void register() {

    }
}
