package me.olios.plugins.donkeyborder.listeners

import me.olios.plugins.donkeyborder.BorderSize
import me.olios.plugins.donkeyborder.DonkeyAction
import me.olios.plugins.donkeyborder.DonkeyBorder
import org.bukkit.entity.Donkey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener(private val plugin: DonkeyBorder): Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player

        if (player.vehicle is Donkey)
            player.leaveVehicle()  // dismount the player from the donkey

        DonkeyBorder.playerCount-- // add the player to the companion object
        BorderSize(plugin).updateBorderRadius()  // update the border size
    }
}