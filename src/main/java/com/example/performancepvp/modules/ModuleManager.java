package com.example.performancepvp.modules;

import com.example.performancepvp.modules.impl.FpsModule;
import com.example.performancepvp.modules.impl.MovementUtilityModule;
import com.example.performancepvp.modules.impl.PerformanceModule;
import com.example.performancepvp.modules.impl.PingModule;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<Module> modules = new ArrayList<>();

    public static void registerDefaultModules() {
        register(new FpsModule());
        register(new PingModule());
        register(new PerformanceModule());
        register(new MovementUtilityModule());
    }

    public static void register(Module module) {
        modules.add(module);
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static void tick(MinecraftClient client) {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.tick();
            }
        }
    }
}
