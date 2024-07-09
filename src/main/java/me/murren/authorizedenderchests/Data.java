package me.murren.authorizedenderchests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class Data {
    public static HashMap<UUID, HashSet<UUID>> AuthorizedPlayers = new HashMap<>();

    public static void addAuthorizedPlayer(UUID owner, UUID authorized) {
        if(!AuthorizedPlayers.containsKey(owner)) {
            AuthorizedPlayers.put(owner, new HashSet<>());
        }
        AuthorizedPlayers.get(owner).add(authorized);
    }

    public static void removeAuthorizedPlayer(UUID owner, UUID authorized) {
        if(!AuthorizedPlayers.containsKey(owner)) {
            return;
        }
        AuthorizedPlayers.get(owner).remove(authorized);
    }

    public static boolean isAuthorized(UUID authorized, UUID owner) {
        return AuthorizedPlayers.containsKey(owner) && AuthorizedPlayers.get(owner).contains(authorized);
    }
}
