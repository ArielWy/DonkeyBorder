package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.entity.Donkey
import org.bukkit.entity.Entity


class BorderSize(private val plugin: DonkeyBorder) {
    private val config = plugin.config
    private val donkey: Entity? = DonkeyBorder.donkey

    fun initialBorderRadius() {
        if (donkey !is Donkey) return

        val world = donkey.world
        val radius: Double = config.getDouble("General.BorderRadius")
        val onlinePlayerCount = Bukkit.getOnlinePlayers().size

        DonkeyBorder.playerCount = onlinePlayerCount
        world.worldBorder.size = DonkeyBorder.playerCount * radius
    }

    fun updateBorderRadius() {
        if (donkey !is Donkey) return

        val world = donkey.world
        val radius: Double = config.getDouble("General.BorderRadius")

        world.worldBorder.size = DonkeyBorder.playerCount * radius
    }
}