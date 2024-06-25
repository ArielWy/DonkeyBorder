package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class DonkeyBorder : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        saveDonkeyUUID()

        registerCommands()
    }

    companion object {
        var donkey: Entity? = null
    }

    private fun registerCommands() {
        getCommand("setborder")?.setExecutor(SetBorderCommand(this))
    }

    private fun saveDonkeyUUID() {
        val donkeyUUIDString = config.getString("DonkeyUUID")
        if (donkeyUUIDString != null) {
            val donkeyUUID = UUID.fromString(donkeyUUIDString)
            val donkey = Bukkit.getEntity(donkeyUUID)
            if (donkey != null) {
                DonkeyBorder.donkey = donkey  // Define the donkey in the plugin
            } else {
                DonkeyBorder.donkey = null  // The mob is no longer in the world or doesn't exist
            }
        }
    }


    override fun onDisable() {
        // Plugin shutdown logic
    }
}