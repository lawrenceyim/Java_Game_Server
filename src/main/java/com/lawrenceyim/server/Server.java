package com.lawrenceyim.server;

import player.Player;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Server {
    private final HashMap<Long, Player> players = new HashMap<>();
    private final int ticksPerSecond = 25;
    private final long tickIntervalInMilliseconds = 1000 / ticksPerSecond;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        Runnable gameTick = () -> {
            // process input as they come in real-time and then store them in a queue in different service
            // relay the stored events to all players
        };

        scheduler.scheduleAtFixedRate(gameTick, 0, tickIntervalInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
