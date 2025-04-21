package com.lawrenceyim.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private static Server instance;
    private final int ticksPerSecond = 25;
    private final long tickIntervalInMilliseconds = 1000 / ticksPerSecond;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<ServerListener> listeners = new ArrayList<>(); // add services that need to be updated each tick

    private Server() {

    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }

        return instance;
    }

    public void start() {
        Runnable gameTick = () -> {
            updateListeners();
            // process input as they come in real-time and then store them in a queue in different service
            // relay the stored events to all players
        };

        scheduler.scheduleAtFixedRate(gameTick, 0, tickIntervalInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

    public void addListener(ServerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ServerListener listener) {
        listeners.remove(listener);
    }

    private void updateListeners() {
        listeners.forEach(listener -> listener.updateListener());
    }
}
