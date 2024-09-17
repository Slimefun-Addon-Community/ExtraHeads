package io.github.thebusybiscuit.extraheads.listeners;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.weapons.SwordOfBeheading;

public class HeadListener implements Listener {

    private final ExtraHeads plugin;

    public HeadListener(ExtraHeads plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e) {
        var registry = ExtraHeads.getRegistry();
        var entityType = e.getEntityType();
        double chance = getChance(entityType, e.getEntity().getKiller());

        if (ThreadLocalRandom.current().nextInt(100) < chance) {
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), registry.getHeads().get(entityType).getItem().clone());
        }
    }

    private double getChance(@Nonnull EntityType type, @Nullable Player killer) {
        if (!ExtraHeads.getRegistry().getHeads().containsKey(type)) {
            return 0;
        }
        double chance = ExtraHeads.getRegistry().getConfig().getDouble("chances." + type);

        if (killer == null) {
            return chance;
        }

        ItemStack item = killer.getInventory().getItemInMainHand();
        SwordOfBeheading sword = (SwordOfBeheading) SlimefunItems.SWORD_OF_BEHEADING.getItem();
        if (item.isEmpty() || sword == null || !sword.isItem(item) || sword.isDisabledIn(killer.getWorld())) {
            return chance;
        }

        return chance * ExtraHeads.getRegistry().getConfig().getDouble("options.sword-of-beheading-multiplier");
    }
}
