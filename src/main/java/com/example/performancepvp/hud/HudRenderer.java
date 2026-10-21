package com.example.performancepvp.hud;

import com.example.performancepvp.PerformancePvP;
import com.example.performancepvp.modules.Module;
import com.example.performancepvp.modules.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class HudRenderer {
    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!PerformancePvP.hudVisible) {
                return;
            }

            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null) {
                return;
            }

            TextRenderer textRenderer = mc.textRenderer;

            int x = 12;
            int y = 12;

            drawString(drawContext, textRenderer, "PerformancePvP", x, y, 0x5CFF9F);
            drawString(drawContext, textRenderer, "FPS: " + mc.getFps(), x, y + 12, 0xFFFFFF);
            drawString(drawContext, textRenderer, "Memory: " + getMemoryUsageMB() + " MB", x, y + 24, 0xFFFFFF);

            if (mc.player != null) {
                drawString(drawContext, textRenderer,
                        String.format("XYZ: %.1f %.1f %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ()),
                        x, y + 36, 0xFFFFFF
                );
            }

            drawString(drawContext, textRenderer, "Modules:", x, y + 52, 0xFFFFFF);

            int line = 64;
            for (Module module : ModuleManager.getModules()) {
                drawString(drawContext, textRenderer,
                        (module.isEnabled() ? "[ON] " : "[OFF] ") + module.getName(),
                        x, y + line, module.isEnabled() ? 0x5CFF9F : 0xA0A0A0
                );
                line += 12;
            }
        });
    }

    private static void drawString(DrawContext drawContext, TextRenderer renderer, String text, int x, int y, int color) {
        drawContext.drawText(renderer, Text.literal(text), x, y, color, false);
    }

    private static long getMemoryUsageMB() {
        Runtime runtime = Runtime.getRuntime();
        long used = runtime.totalMemory() - runtime.freeMemory();
        return used / (1024 * 1024);
    }
}
