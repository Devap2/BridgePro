package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.entity.EntityType;
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

        // Disable food level change.
        if(e.getEntityType().equals(EntityType.PLAYER)){
            if(plugin.getConfig().getBoolean("disable-hunger")){
                e.setCancelled(true);
            }
        }
    }
}
