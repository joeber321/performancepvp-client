package com.example.performancepvp.modules;

public abstract class Module {
    private final String name;
    private final String description;
    private boolean enabled;
    private final boolean toggleable;

    protected Module(String name, String description, boolean toggleable) {
        this.name = name;
        this.description = description;
        this.toggleable = toggleable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isToggleable() {
        return toggleable;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void tick() {}
    public void onEnable() {}
    public void onDisable() {}
}
