package io.github.thebusybiscuit.extraheads;

import javax.annotation.Nonnull;

import lombok.Getter;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.extraheads.listeners.HeadListener;
import io.github.thebusybiscuit.extraheads.setup.ItemSetup;
import io.github.thebusybiscuit.extraheads.setup.Registry;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.BlobBuildUpdater;

public class ExtraHeads extends JavaPlugin implements SlimefunAddon {

    @Getter
    private static ExtraHeads instance;

    private Registry registry;

    public static Registry getRegistry() {
        return getInstance().registry;
    }

    @Override
    public void onEnable() {
        instance = this;

        // registry and config
        registry = new Registry(new Config(this));

        // Setting up bStats
        new Metrics(this, 5650);

        if (registry.getConfig().getBoolean("options.auto-update") && getPluginVersion().startsWith("Dev")) {
            new BlobBuildUpdater(this, getFile(), "ExtraHeads").start();
        }

        ItemSetup.setup();

        new HeadListener(this);
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    @Nonnull
    public String getBugTrackerURL() {
        return "https://github.com/Slimefun-Addon-Community/ExtraHeads/issues";
    }
}
