package dev.playeresp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public final class PlayerEsp implements ClientModInitializer {
    private static boolean enabled;

    public static boolean isEnabled() {
        return enabled;
    }

    @Override
    public void onInitializeClient() {
        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.playeresp.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O,
                "category.playeresp"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                if (client.player == null || client.currentScreen != null) {
                    continue;
                }

                enabled = !enabled;
                client.player.sendMessage(Text.translatable(
                        enabled ? "message.playeresp.enabled" : "message.playeresp.disabled"), true);
            }
        });
    }
}
