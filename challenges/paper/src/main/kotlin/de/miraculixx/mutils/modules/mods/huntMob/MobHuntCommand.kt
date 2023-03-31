package de.miraculixx.mutils.modules.mods.huntMob

import de.miraculixx.api.modules.mods.huntStuff.HuntCommand
import de.miraculixx.mutils.extensions.enumOf
import de.miraculixx.mutils.utils.getLivingMobs
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.EntityType

class MobHuntCommand(private val data: MobHunt) : TabExecutor, HuntCommand<EntityType> {
    override val typeList = getLivingMobs(true)
    override val typeNameList = getLivingMobs(true).map { it.name }
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return getTabComplete(args, data.blacklist.map { it.name })
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        data.handleCommand(sender, sender.name, args)
        return true
    }

    override fun getType(input: String?) = enumOf<EntityType>(input)
}