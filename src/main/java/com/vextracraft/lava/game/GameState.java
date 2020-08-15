package com.vextracraft.lava.game;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ethan Borawski
 */
public class GameState {

    private boolean canBuild;
    private boolean canBreak;
    private boolean canPvP;
    private boolean canMove;
    private boolean canUseCommands;

    private Set<Material> allowedBlocks;
    private Set<String> allowedCommands;
    private GameStateType stateType;

    public GameState() {
        stateType = GameStateType.PRE_GAME;

        this.canBuild = false;
        this.canBreak = false;
        this.canPvP = false;
        this.canMove = true;
        this.canUseCommands = true;

        allowedCommands = new HashSet<String>();
    }

    public boolean canBuild() {
        return canBuild;
    }

    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    public boolean canBreak() {
        return canBreak;
    }

    public void setCanBreak(boolean canBreak) {
        this.canBreak = canBreak;
    }

    public boolean canPvP() {
        return canPvP;
    }

    public void setCanPvP(boolean canPvP) {
        this.canPvP = canPvP;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canUseCommands() {
        return canUseCommands;
    }

    public void setCanUseCommands(boolean canUseCommands) {
        this.canUseCommands = canUseCommands;
    }

    public Set<String> getAllowedCommands() {
        return allowedCommands;
    }

    public GameStateType getStateType() {
        return stateType;
    }
}
