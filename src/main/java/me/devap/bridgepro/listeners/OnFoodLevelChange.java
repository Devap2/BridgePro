package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnFoodLevelChange implements Listener {

    private final BridgePro plugin;

    public OnFoodLevelChange(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){

        Player p = (Player) e.getEntity();

        // Disable food level change.
        if(e.getEntityType().equals(EntityType.PLAYER)){
            if(plugin.getConfig().getBoolean("disable-hunger")){
                if(plugin.ingame.contains(p)){
                    e.setCancelled(true);
                }
            }
            else{
                e.setCancelled(false);
            }
        }
    }
}
