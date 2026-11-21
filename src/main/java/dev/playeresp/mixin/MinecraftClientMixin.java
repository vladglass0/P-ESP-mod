package dev.playeresp.mixin;

import dev.playeresp.PlayerEsp;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
abstract class MinecraftClientMixin {
    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    private void playeresp$showPlayerOutline(Entity entity, CallbackInfoReturnable<Boolean> result) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        if (PlayerEsp.isEnabled() && entity instanceof PlayerEntity && entity != client.player) {
            result.setReturnValue(true);
        }
    }
}
