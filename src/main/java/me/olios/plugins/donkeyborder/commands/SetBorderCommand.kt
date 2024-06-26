package me.olios.plugins.donkeyborder.commands

import me.olios.plugins.donkeyborder.DonkeyAction
import me.olios.plugins.donkeyborder.DonkeyBorder
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetBorderCommand(private val plugin: DonkeyBorder): CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (sender !is Player) return false
        DonkeyAction(sender, plugin).checkDonkey()
        return true
    }
}