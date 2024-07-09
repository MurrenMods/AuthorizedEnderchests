package me.murren.authorizedenderchests;


import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.UUID;

public class PersistentDataHandler {
    private static final String FILE_NAME = "plugins/AuthorizedEnderchests/data.txt";

    public static void saveData() {
        // Save data to string
        String data = "";
        for(UUID owner : Data.AuthorizedPlayers.keySet()) {
            data += owner.toString() + ":";
            for(UUID authorized : Data.AuthorizedPlayers.get(owner)) {
                data += authorized.toString() + ",";
            }
            data += "\n";
        }
        // Write data to file
        try {
            java.io.File file = new java.io.File(FILE_NAME);
            file.getParentFile().mkdirs();
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadData() {
        // Load data from file
        try {
            // Read data from file
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                UUID owner = UUID.fromString(parts[0]);
                HashSet<UUID> authorizedPlayers = new HashSet<>();
                for(String authorized : parts[1].split(",")) {
                    authorizedPlayers.add(UUID.fromString(authorized));
                }
                Data.AuthorizedPlayers.put(owner, authorizedPlayers);
            }
            reader.close();
        } catch (Exception e) {
            if(e instanceof java.io.FileNotFoundException) {
                Bukkit.getLogger().warning("Failed to load data from file: " + FILE_NAME +
                        ". If you just installed this plugin, you can safely ignore this warning and the file will be created on shutdown. " +
                        "Otherwise, please check the file and make sure it is in the correct format.");

            }
            else {
                e.printStackTrace();
            }
        }
    }
}
