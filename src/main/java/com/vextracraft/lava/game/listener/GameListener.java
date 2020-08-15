package com.vextracraft.lava.game.listener;

import com.vextracraft.lava.LavaPlugin;
import com.vextracraft.lava.game.GameState;
import com.vextracraft.lava.game.GameStateType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author Ethan Borawski
 */
public class GameListener implements Listener {

    private LavaPlugin plugin;
    private GameState gameState;

    public GameListener(LavaPlugin plugin, GameState gameState) {
        this.plugin = plugin;
        this.gameState = gameState;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public LavaPlugin getPlugin() {
        return plugin;
    }

    public GameState getGameState() {
        return gameState;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if(getGameState().getStateType() != GameStateType.PRE_GAME)
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Lava Survival has already started!");

    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        event.setCancelled(!gameState.canBuild());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(!gameState.canBreak());
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player
                && event.getEntity() instanceof Player) {

            event.setCancelled(!gameState.canPvP());
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        int x0 = event.getFrom().getBlockX();
        int y0 = event.getFrom().getBlockY();
        int z0 = event.getFrom().getBlockZ();

        int x1 = event.getTo().getBlockX();
        int y1 = event.getTo().getBlockY();
        int z1 = event.getTo().getBlockZ();

        event.setCancelled(!(x0 != x1 || y0 != y1 || z0 != z1));
    }

    @EventHandler
    public void onCommand(AsyncPlayerChatEvent event) {
        if(gameState.canUseCommands()) {
            String command = event.getMessage().split(" ")[0];
            event.setCancelled(!gameState.getAllowedCommands().contains(command));
        }
    }


}
