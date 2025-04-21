package player;

import com.lawrenceyim.server.Server;
import com.lawrenceyim.server.ServerListener;

import java.util.HashMap;

public class PlayerManager implements ServerListener {
    private final HashMap<Long, Player> players = new HashMap<>(); // probably move to another service

    public PlayerManager() {
        Server.getInstance().addListener(this);
    }

    @Override
    public void updateListener() {
        players.values().forEach(player -> {
            // update players via udp?
        });
    }
}
