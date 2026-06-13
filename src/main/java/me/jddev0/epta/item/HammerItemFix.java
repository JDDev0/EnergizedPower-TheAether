package me.jddev0.epta.item;

import me.jddev0.ep.component.EPDataComponentTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;

public class HammerItemFix extends TieredItem {
    private final RandomSource random = RandomSource.create();

    public HammerItemFix(Tier tier, Item.Properties props) {
        super(tier, props.component(EPDataComponentTypes.NO_REPAIR, Unit.INSTANCE));
    }

    public boolean isValidRepairItem(ItemStack itemStack, ItemStack ingredient) {
        return false;
    }

    public ItemStack getRecipeRemainder(ItemStack itemStack) {
        ItemStack copy = itemStack.copy();
        if (copy.isDamageableItem()) {
            int i = copy.getDamageValue() + 1;
            copy.setDamageValue(i);
            if (i >= copy.getMaxDamage()) {
                copy.setCount(0);
            }
        }

        return copy;
    }

    public boolean hasCraftingRemainingItem() {
        return true;
    }
}
