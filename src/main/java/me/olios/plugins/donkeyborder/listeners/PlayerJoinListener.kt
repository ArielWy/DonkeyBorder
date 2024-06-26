package me.olios.plugins.donkeyborder.listeners

import me.olios.plugins.donkeyborder.BorderSize
import me.olios.plugins.donkeyborder.DonkeyBorder
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val plugin: DonkeyBorder): Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        DonkeyBorder.playerCount++ // add the player to the companion object
        BorderSize(plugin).updateBorderRadius()  // update the border size
    }
}