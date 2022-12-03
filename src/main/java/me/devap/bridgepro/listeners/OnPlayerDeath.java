package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeath implements Listener {

    private final BridgePro plugin;

    public OnPlayerDeath(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        Player p = e.getEntity().getPlayer();
        assert p != null;
        String pname = p.getName();

        // If player is in-game, set the death message to ...
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            if(plugin.ingame.contains(p)){
                e.getDrops().clear();
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&n&7" + pname + "&r&7 thought they could survive in the void."));
            }
        }
    }
}
