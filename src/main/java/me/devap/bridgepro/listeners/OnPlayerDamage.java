package me.devap.bridgepro.listeners;

import me.devap.bridgepro.BridgePro;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamage implements Listener {

    private final BridgePro plugin;

    public OnPlayerDamage(BridgePro plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){

        // If the damage cause is because of a fire tick and disabled in config, cancel event.
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
            if(plugin.getConfig().getBoolean("disable-fire-damage")){
                e.setCancelled(true);
            }
        }
        // If the damage cause is because of fire and disabled in config, cancel event.
        else if(e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){
            if(plugin.getConfig().getBoolean("disable-lava-damage")){
                e.setCancelled(true);
            }
        }
        // If the damage cause is because of fall-damage and disabled in config, cancel event.
        else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            if(e.getEntityType().equals(EntityType.PLAYER)){
                if(plugin.getConfig().getBoolean("disable-fall-damage")){
                    e.setCancelled(true);
                }
            }
        }
        else{
            e.setCancelled(false);
        }
    }
}
