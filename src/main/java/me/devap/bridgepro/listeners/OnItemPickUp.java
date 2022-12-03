package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class OnItemPickUp implements Listener {

    private final BridgePro plugin;

    public OnItemPickUp(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e){

        Player p = (Player) e.getEntity();

        // Disable players from picking up items when in lobby or in-game.
        if(e.getEntityType().equals(EntityType.PLAYER)){
            if(plugin.ingame.contains(p) || plugin.lobby.contains(p)){
                if(plugin.getConfig().getBoolean("disable-item-pick-up")){
                    e.setCancelled(true);
                }
            }
        }
    }
}
