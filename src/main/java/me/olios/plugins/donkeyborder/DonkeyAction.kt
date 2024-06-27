package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.io.IOException
import java.util.UUID

class DonkeyAction(private val player: Player, private val plugin: DonkeyBorder) {

    fun checkDonkey() {
        val donkey: Entity? = DonkeyBorder.donkey
        when {
            donkey == null -> summonDonkey()
            donkey.isValid && !donkey.isDead -> moveDonkey()
            else -> summonDonkey()
        }
    }


    private fun summonDonkey() {
        val donkey = player.world.spawnEntity(player.location, EntityType.DONKEY) // spawn the donkey
        donkey.isInvulnerable = true  // mob can't die
        donkey.isPersistent = true  // mob can't despawn
        val donkeyUUID = donkey.uniqueId // get the entity UUID

        DefineMob(plugin).defineDonkey(donkeyUUID.toString()) // save the UUID in the config
        DonkeyBorder.donkey = donkey // define the donkey in the plugin

        TickHandler(plugin).tickUpdate() // update every certain ticks
        BorderSize(plugin).initialBorderRadius()  // initial the border radius

        player.sendMessage("§b[§6DonkeyBorder§b] §aSummon the donkey and reset the border")  // send a message to the player
    }

    private fun moveDonkey() {
        val donkey: Entity = DonkeyBorder.donkey!!
        Bukkit.getScheduler().runTask(plugin, Runnable { donkey.teleport(player.location) })
        player.sendMessage("§b[§6DonkeyBorder§b] §9teleport the donkey to your location")
    }
}