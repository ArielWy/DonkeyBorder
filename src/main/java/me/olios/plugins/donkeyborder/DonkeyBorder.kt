package me.olios.plugins.donkeyborder

import me.olios.plugins.donkeyborder.commands.SetBorderCommand
import me.olios.plugins.donkeyborder.commands.TeleportDonkeyCommand
import me.olios.plugins.donkeyborder.listeners.PlayerJoinListener
import me.olios.plugins.donkeyborder.listeners.PlayerQuitListener
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class DonkeyBorder : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()
        DefineMob(this).saveDonkeyUUID()

        registerCommands()
        registerListeners()
    }

    companion object {
        var donkey: Entity? = null
        var playerCount: Int = 0
    }

    private fun registerCommands() {
        getCommand("setborder")?.setExecutor(SetBorderCommand(this))
        getCommand("tpdonkey")?.setExecutor(TeleportDonkeyCommand(this))
    }

    private fun registerListeners() {
        Bukkit.getServer().pluginManager.registerEvents(PlayerJoinListener(this), this)
        Bukkit.getServer().pluginManager.registerEvents(PlayerQuitListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}