package me.olios.plugins.donkeyborder.commands

import me.olios.plugins.donkeyborder.DonkeyAction
import me.olios.plugins.donkeyborder.DonkeyBorder
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

class DonkeyBorderCommand(private val plugin: DonkeyBorder): CommandExecutor, TabExecutor {

    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("donkeyborder.admin")) return false

        val command = p3?.getOrElse(0) { return false }?.lowercase()

        when (command) {
            "reload" -> reloadPlugin(sender)
            "setborder" -> DonkeyAction(sender, plugin).checkDonkey()
            "tpdonkey" -> TeleportDonkeyCommand(plugin).teleportPlayer(sender)
            else -> return false
        }

        return true
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>?
    ): MutableList<String>? {
        if (p1.name.equals("banbook", ignoreCase = true)) {
            if (p3!!.size == 1) {
                return mutableListOf("reload", "setborder", "tpdonkey")
            }
        }
        return null
    }


    private fun reloadPlugin(player: Player) {
        try {
            plugin.reloadConfig()
            val defineFile = File(plugin.dataFolder, "define.yml")
            YamlConfiguration.loadConfiguration(defineFile)
            player.sendMessage("§b[§6DonkeyBorder§b] §athe plugin has been reloaded!")
        } catch (exception: Exception) {
            // Log the error
            plugin.logger.severe("Failed to reload configuration: ${exception.message}")

            // Send a message to the player
            player.sendMessage("§b[§6DonkeyBorder§b] §cThe plugin failed to reload. Please check the server logs for more details.")

            // Reload the original file
            plugin.saveDefaultConfig()
            plugin.reloadConfig()
            player.sendMessage("§b[§6DonkeyBorder§b] §aThe original configuration has been reloaded.")
        }
    }

}