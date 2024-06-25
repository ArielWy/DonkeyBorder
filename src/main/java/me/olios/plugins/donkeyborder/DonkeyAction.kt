package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.io.IOException
import java.util.UUID

class DonkeyAction(private val player: Player, private val plugin: DonkeyBorder) {
    private val config = plugin.config

    fun checkDonkey() {
        val donkey: Entity = DonkeyBorder.donkey!!
        if (donkey.isDead)
            summonDonkey()
        else moveDonkey()
    }

    private fun summonDonkey() {
        val donkey = player.world.spawnEntity(player.location, EntityType.DONKEY) // spawn the donkey
        donkey.isInvulnerable = true  // cant die
        val donkeyUUID = donkey.uniqueId // get the entity UUID

        defineItem(donkeyUUID.toString()) // save the uuid in the config
        DonkeyBorder.donkey = donkey // define the donkey in the plugin

    }

    private fun moveDonkey() {
        val donkey: Entity = DonkeyBorder.donkey!!
        Bukkit.getScheduler().runTask(plugin, Runnable { donkey.teleport(player.location) })
        player.sendMessage("§9teleport the donkey to your location")
    }

    private fun defineItem(StringUUID: String) {
        config.set("DonkeyUUID", StringUUID)
        config.set("Active", true)

        try { // try to save the file
            plugin.saveConfig()
            plugin.reloadConfig()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}