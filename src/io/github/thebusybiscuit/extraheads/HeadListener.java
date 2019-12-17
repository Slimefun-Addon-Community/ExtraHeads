package io.github.thebusybiscuit.extraheads;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

public class HeadListener implements Listener {
	
	private ExtraHeads plugin;
	
	public HeadListener(ExtraHeads plugin) {
		this.plugin = plugin;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onKill(EntityDeathEvent e) {
		if (!plugin.getMobDrops().containsKey(e.getEntityType())) {
			return;
		}
		
		double chance = plugin.getCfg().getDouble("chances." + e.getEntityType().toString());
		Player killer = e.getEntity().getKiller();
		
		if (killer != null && SlimefunManager.isItemSimilar(killer.getInventory().getItemInMainHand(), SlimefunItems.SWORD_OF_BEHEADING, true)) {
			chance *= plugin.getCfg().getDouble("options.sword-of-beheading-multiplier");
		}
		
		if (Math.random() * 100 < chance) {
			e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), plugin.getMobDrops().get(e.getEntityType()));
		}
	}

}
