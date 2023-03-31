package de.miraculixx.mutils.modules.mods.huntItems

import de.miraculixx.api.modules.mods.huntStuff.HuntCommand
import de.miraculixx.mutils.extensions.enumOf
import de.miraculixx.mutils.utils.getMaterials
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class ItemHuntCommand(private val data: ItemHunt): TabExecutor, HuntCommand<Material> {
    override val typeList = getMaterials(true)
    override val typeNameList = getMaterials(true).map { it.name }
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return getTabComplete(args, data.blacklist.map { it.name })
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        data.handleCommand(sender, sender.name, args)
        return true
    }

    override fun getType(input: String?) = enumOf<Material>(input)
}