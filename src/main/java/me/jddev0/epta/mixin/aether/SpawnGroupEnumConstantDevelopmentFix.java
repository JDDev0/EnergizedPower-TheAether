package me.jddev0.epta.mixin.aether;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobCategory.class)
public abstract class SpawnGroupEnumConstantDevelopmentFix {
    @Inject(method = "valueOf", at = @At("HEAD"), cancellable = true)
    private static void valueOf(String name, CallbackInfoReturnable<MobCategory> cir) {
        //Fix should only run in development environment
        if(FabricLoader.getInstance().isDevelopmentEnvironment() && name.startsWith("AETHER"))
            cir.setReturnValue(MobCategory.MONSTER);
    }
}
