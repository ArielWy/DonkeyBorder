package me.olios.plugins.donkeyborder

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.scheduler.BukkitRunnable


class TickHandler(private val plugin: DonkeyBorder) {
    private val donkey: Entity = DonkeyBorder.donkey!!
    private val config = plugin.config

    fun tickUpdate() {
        val ticksDelay: Long = config.getLong("General.UpdateDelayTicks")
        object : BukkitRunnable() {
            override fun run() {
                if (DonkeyBorder.donkey == null || donkey.isDead) {
                    println("§a${DonkeyBorder.donkey}, ${donkey.isValid}")
                    cancel()
                }

                borderUpdate()  // update the border
                onSurface()  // teleport if not on the surface
                icePath()  //  ice path on water
            }
        }.runTaskTimer(plugin, ticksDelay, 10)  // run the task in a separate thread
    }

    fun borderUpdate() {  // update the border center location
        val world = donkey.world
        val donkeyLocation = donkey.location

        world.worldBorder.center = donkeyLocation // update the center
    }

    fun onSurface() {
        val donkeyLocation = donkey.location
        val highestLocation = donkey.location.toHighestLocation().add(0.0, 1.0, 0.0)

        val donkeyY = donkeyLocation.blockY
        val highestY = highestLocation.blockY

        if (!donkey.isOnGround && donkeyLocation.block.isSolid && donkeyY != highestY)
            Bukkit.getScheduler().runTask(plugin, Runnable { donkey.teleport(highestLocation) })
    }

    fun icePath() {
        val radius: Int = config.getInt("General.IcePathRadius")
        val donkeyLocation = donkey.location
        val x: Int = donkeyLocation.blockX
        val y: Int = donkeyLocation.blockY - 1
        val z: Int = donkeyLocation.blockZ

        for (i in x - radius..x + radius) {
            for (j in z - radius..z + radius) {
                val block = donkeyLocation.world.getBlockAt(i, y, j)
                if (block.type != Material.WATER) continue
                block.type = Material.FROSTED_ICE
            }
        }
    }

}