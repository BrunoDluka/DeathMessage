package me.death.pack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		Player k = e.getEntity().getKiller();
		List<ItemStack> drops = e.getDrops();
		if (getConfig().getBoolean("enabled") == true) {
			e.setDeathMessage(null);

			if (getConfig().getBoolean("dropItens") == false) {
				drops.clear();
			}

			if (getConfig().getBoolean("keepInventory") == true) {
				e.setKeepInventory(true);
			}

			e.setDroppedExp(getConfig().getInt("droppedXp"));

			if (k == null) {
				Bukkit.broadcastMessage(
						getConfig().getString("morreuSozinho").replace("&", "§").replace("%player%", p.getName()));
				return;
			} else {
				Bukkit.broadcastMessage(getConfig().getString("morreuPor").replace("&", "§")
						.replace("%player%", p.getName()).replace("%killer%", k.getName()));
				return;
			}
		} else {
			e.setDeathMessage(null);
		}
	}
}
