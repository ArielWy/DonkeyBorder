package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class DonkeyBorder : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        DefineMob(this).saveDonkeyUUID()

        registerCommands()
    }

    companion object {
        var donkey: Entity? = null
    }

    private fun registerCommands() {
        getCommand("setborder")?.setExecutor(SetBorderCommand(this))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}