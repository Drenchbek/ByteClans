package net.bytemc.anarchy.clans.database;

import net.bytemc.anarchy.clans.ByteClans;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private final ByteClans plugin;
    private Connection connection;

    public DatabaseManager(ByteClans plugin) { 
        this.plugin = plugin; 
    }

    public void init() {
        File dbFile = new File(plugin.getDataFolder(), "clans.db");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
            try (Statement s = connection.createStatement()) {
                s.execute("CREATE TABLE IF NOT EXISTS byte_clans (clan_name TEXT PRIMARY KEY, war_points INT, clan_rank TEXT);");
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    public void close() { 
        try { 
            if (connection != null) connection.close(); 
        } catch (Exception e) {} 
    }
}
