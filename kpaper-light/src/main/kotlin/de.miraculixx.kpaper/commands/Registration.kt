package de.miraculixx.kpaper.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import de.miraculixx.kpaper.annotations.NMS_General
import de.miraculixx.kpaper.commands.internal.BrigardierSupport
import net.minecraft.commands.CommandSourceStack

/**
 * Registers this command at the [CommandDispatcher] of the server.
 *
 * @param sendToPlayers whether the new command tree should be send to
 * all players, this is true by default, but you can disable it if you are
 * calling this function as the server is starting
 */
@NMS_General
fun LiteralArgumentBuilder<CommandSourceStack>.register(sendToPlayers: Boolean = true) {
    if (!BrigardierSupport.executedDefaultRegistration)
        BrigardierSupport.commands += this
    else {
        BrigardierSupport.ResolveCommandManager.manager.dispatcher.register(this)
        if (sendToPlayers)
            BrigardierSupport.updateCommandTree()
    }
}
