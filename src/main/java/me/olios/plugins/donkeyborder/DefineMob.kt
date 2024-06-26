package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.Listener
import java.io.File
import java.io.IOException
import java.util.*

class DefineMob(private val plugin: DonkeyBorder): Listener {
    private val defineFile: File = File(plugin.dataFolder, "define.yml")
    private val defineConfig: FileConfiguration = YamlConfiguration.loadConfiguration(defineFile)

    private val defineMobPath: String = "DonkeyUUID"

    init {
        // Load or create the define.yml configuration file
        if (!defineFile.exists())
            plugin.saveResource("define.yml", false)
    }

    fun defineDonkey(StringUUID: String) {
        defineConfig.set(defineMobPath, StringUUID)

        try { // try to save the file
            defineConfig.save(defineFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveDonkeyUUID() {
        val donkeyUUIDString = defineConfig.getString(defineMobPath)
        if (donkeyUUIDString != null) {
            val donkeyUUID = UUID.fromString(donkeyUUIDString)
            val donkey = Bukkit.getEntity(donkeyUUID)
            if (donkey != null) {
                DonkeyBorder.donkey = donkey  // Define the donkey in the plugin
                TickHandler(plugin).tickUpdate() // update every certain ticks
                BorderSize(plugin).initialBorderRadius()  // initial the border size
            } else {
                DonkeyBorder.donkey = null  // The mob is no longer in the world or doesn't exist
            }
        }
    }
}