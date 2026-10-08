package net.bytemc.anarchy.clans;
import org.bukkit.plugin.java.JavaPlugin;
import net.bytemc.anarchy.clans.database.DatabaseManager;
import net.bytemc.anarchy.clans.listeners.ClanEventListener;
public final class ByteClans extends JavaPlugin {
    private static ByteClans instance;
    private DatabaseManager databaseManager;
    @Override
    public void onEnable() {
        instance = this;
        this.databaseManager = new DatabaseManager(this);
        this.databaseManager.init();
        getServer().getPluginManager().registerEvents(new ClanEventListener(this), this);
        getLogger().info("ByteClans 1.21.1 Obfuscated successfully loaded.");
    }
    @Override
    public void onDisable() { this.databaseManager.close(); }
    public static ByteClans getInstance() { return instance; }
}
