package com.vextracraft.lava.game;

import com.vextracraft.lava.LavaPlugin;
import com.vextracraft.lava.game.task.GameLavaTask;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ethan Borawski
 */
public class Game {

    private LavaPlugin plugin;
    private int minimumPlayers;
    private int maximumPlayers;
    private GameState state;
    private Set<UUID> set;

    public Game(LavaPlugin plugin) {
        this.plugin = plugin;
        this.minimumPlayers = 4;
        this.maximumPlayers = 20;

        this.state = new GameState();
        this.set = new HashSet<UUID>();
    }

    public LavaPlugin getPlugin() {
        return plugin;
    }

    public int getMinimumPlayers() {
        return minimumPlayers;
    }

    public int getMaximumPlayers() {
        return maximumPlayers;
    }

    public GameState getState() {
        return state;
    }

    public Set<UUID> getPlayers() {
        return set;
    }

    public void start() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, new GameLavaTask(this),0, 20 * 3);

    }
}
