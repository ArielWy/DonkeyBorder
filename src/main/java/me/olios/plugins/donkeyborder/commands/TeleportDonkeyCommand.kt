package me.olios.plugins.donkeyborder.commands

import me.olios.plugins.donkeyborder.DonkeyBorder
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class TeleportDonkeyCommand(private val plugin: DonkeyBorder): CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (sender !is Player) return false
        return teleportPlayer(sender)
    }

    fun teleportPlayer(player: Player): Boolean {
        if (DonkeyBorder.donkey == null) {
            player.sendMessage("§cDo </setborder> to summoned the Donkey")
            return false
        } else {
            val donkey: Entity = DonkeyBorder.donkey!!
            Bukkit.getScheduler().runTask(plugin, Runnable { player.teleport(donkey.location) })
            player.sendMessage("§9teleport §7${player.name} §9to the Donkey location")
        }

        return true
    }
}