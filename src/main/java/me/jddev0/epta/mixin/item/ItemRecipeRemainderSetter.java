package me.jddev0.epta.mixin.item;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemRecipeRemainderSetter {
    @Accessor("craftingRemainingItem")
    @Mutable
    void setCraftingRemainingItem(Item item);
}
