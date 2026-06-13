package me.jddev0.epta.mixin.aether;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookType.class)
public abstract class RecipeBookCategoryEnumConstantDevelopmentFix {
    @Inject(method = "valueOf", at = @At("HEAD"), cancellable = true)
    private static void valueOf(String name, CallbackInfoReturnable<RecipeBookType> cir) {
        //Fix should only run in development environment
        if(FabricLoader.getInstance().isDevelopmentEnvironment() && name.startsWith("AETHER"))
            cir.setReturnValue(RecipeBookType.CRAFTING);
    }
}
