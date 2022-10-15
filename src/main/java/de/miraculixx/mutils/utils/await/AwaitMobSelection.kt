package de.miraculixx.mutils.utils.await

import de.miraculixx.mutils.Manager
import de.miraculixx.mutils.enums.settings.gui.GUI
import de.miraculixx.mutils.enums.settings.gui.GUIAnimation
import de.miraculixx.mutils.enums.settings.gui.StorageFilter
import de.miraculixx.mutils.modules.challenge.utils.getLivingMobs
import de.miraculixx.mutils.utils.gui.GUIBuilder
import de.miraculixx.mutils.utils.gui.items.ItemLib
import de.miraculixx.mutils.utils.gui.items.PDCValues
import de.miraculixx.mutils.utils.gui.items.buildItem
import de.miraculixx.mutils.utils.text.*
import de.miraculixx.mutils.utils.tools.enumOf
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * gui.await.mob
 */
class AwaitMobSelections(player: Player, filter: String, preset: GUI, random: Boolean, callback: (ItemStack) -> Unit) {
    private val lore = listOf(emptyComponent(), cmp("Click", cHighlight) + cmp(" ≫ Choose Mob"))
    private val invBuilder = GUIBuilder(player, preset, GUIAnimation.WATERFALL_OPEN)
        .storage(
            StorageFilter.HIDE,
            buildList {
                if (random) addAll(ItemLib().getKeyed(1, "gui.await.mob", filter))
                addAll(
                    getLivingMobs(false)
                    .map { buildItem(enumOf<Material>("${it.name}_SPAWN_EGG") ?: Material.POLAR_BEAR_SPAWN_EGG,
                        -1,
                        cmp(it.name.fancy()), lore, values = listOf(PDCValues(NamespacedKey(Manager, "gui.await.mob"), it.name)))
                    }
                )
            },
            buildItem(
                Material.OAK_SIGN, 0, cmp("Mob Selection", cHighlight, bold = true), listOf(cmp("Search: $filter"))
            )
        )

    init {
        invBuilder.open()
        AwaitInventoryClick(player, invBuilder.get()!!, callback)
    }
}