package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class OnPlayerDeath implements Listener {

    private final BridgePro plugin;

    public OnPlayerDeath(BridgePro plugin) {
        this.plugin = plugin;
    }

    public HashMap<String, Integer> killstreak = new HashMap<String, Integer>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        Player p = e.getEntity().getPlayer();
        String pname = p.getName();

        /*if(e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller().getPlayer();
            Player killed = e.getEntity();
            if(!killstreak.containsKey(killer.getName())) {
                killstreak.put(killer.getName(), 0);
            }
            killstreak.put(killer.getName(), killstreak.get(killer.getName() +1));
            if(killstreak.containsKey(killed.getName())) {
                killstreak.remove(killed.getName());
            }
        }*/

        // If player is in-game, set the death message to ...
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            if(plugin.ingame.contains(p) || plugin.lobby.contains(p)){
                e.getDrops().clear();
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&n&7" + pname + "&r&7 thought they could survive in the void."));
            }
        }
    }
}
