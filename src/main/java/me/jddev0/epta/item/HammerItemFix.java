package me.jddev0.epta.item;

import me.jddev0.ep.component.EPDataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.util.Unit;
import net.minecraft.util.math.random.Random;

public class HammerItemFix extends ToolItem {
    private final Random random = Random.create();

    public HammerItemFix(ToolMaterial tier, Item.Settings props) {
        super(tier, props.component(EPDataComponentTypes.NO_REPAIR, Unit.INSTANCE));
    }

    public boolean canRepair(ItemStack itemStack, ItemStack ingredient) {
        return false;
    }

    public ItemStack getRecipeRemainder(ItemStack itemStack) {
        ItemStack copy = itemStack.copy();
        if (copy.isDamageable()) {
            int i = copy.getDamage() + 1;
            copy.setDamage(i);
            if (i >= copy.getMaxDamage()) {
                copy.setCount(0);
            }
        }

        return copy;
    }

    public boolean hasRecipeRemainder() {
        return true;
    }
}
