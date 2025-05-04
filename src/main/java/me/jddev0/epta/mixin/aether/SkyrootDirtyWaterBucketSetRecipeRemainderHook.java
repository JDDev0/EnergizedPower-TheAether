package me.jddev0.epta.mixin.aether;

import com.aetherteam.aether.Aether;
import me.jddev0.epta.EnergizedPowerTAMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Aether.class)
public abstract class SkyrootDirtyWaterBucketSetRecipeRemainderHook {
    @Inject(method = "onInitialize", at = @At("TAIL"), remap = false)
    private void onInitialize(CallbackInfo ci) {
        EnergizedPowerTAMod.isAetherLoaded = true;
        EnergizedPowerTAMod.onSkyrootDirtyWaterBucketSetRecipeRemainderHook();
    }
}
