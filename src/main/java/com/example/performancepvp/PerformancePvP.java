package com.example.performancepvp;

import com.example.performancepvp.hud.HudRenderer;
import com.example.performancepvp.modules.Module;
import com.example.performancepvp.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class PerformancePvP implements ClientModInitializer {
    public static final String MOD_ID = "performancepvpclient";

    public static final KeyBinding TOGGLE_HUD = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Toggle HUD", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "PerformancePvP")
    );

    public static final KeyBinding TOGGLE_MODULES = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("Toggle Modules", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "PerformancePvP")
    );

    public static boolean hudVisible = true;

    @Override
    public void onInitializeClient() {
        HudRenderer.register();
        ModuleManager.registerDefaultModules();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_HUD.wasPressed()) {
                hudVisible = !hudVisible;
                if (client.player != null) {
                    client.player.sendMessage(Text.literal("HUD " + (hudVisible ? "enabled" : "disabled")), true);
                }
            }

            while (TOGGLE_MODULES.wasPressed()) {
                for (Module module : ModuleManager.getModules()) {
                    if (module.isToggleable()) {
                        module.setEnabled(!module.isEnabled());
                    }
                }

                if (client.player != null) {
                    client.player.sendMessage(Text.literal("Modules toggled"), true);
                }
            }

            ModuleManager.tick(client);
        });

        System.out.println("PerformancePvP initialized.");
    }
}
