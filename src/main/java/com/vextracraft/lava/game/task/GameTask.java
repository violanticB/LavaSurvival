package com.vextracraft.lava.game.task;

/**
 * @author Ethan Borawski
 */
public interface GameTask extends Runnable {

    /**
     * Game Task occurs every x amount of ticks,
     * the frequency.
     * @return frequency
     */
    int frequencyTicks();

}
