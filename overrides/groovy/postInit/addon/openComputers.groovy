// MODS_LOADED: opencomputers
// ^, Makes the script not run as long as Open Computers isn't a mod in the player's instance.

package postInit.addon

//import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder
//import gregtech.api.recipes.recipeproperties.ResearchProperty
import com.nomiceu.nomilabs.util.LabsModeHelper
import net.minecraft.item.ItemStack

import static gregtech.api.GTValues.*

/**
 * Remove items with a GT equivalent
 * These items already have an equivalent within GT and should be hidden
 */
// Hide from JEI
mods.jei.ingredient.hide(ore('oc:materialCircuitBoardRaw')) // Raw circuit board
mods.jei.ingredient.hide(ore('oc:materialCircuitBoardPrinted')) // Printed circuit board
mods.jei.ingredient.hide(ore('oc:materialTransistor')) // Transistor

// Remove Recipes
crafting.remove('opencomputers:material33') // Raw circuit board
crafting.remove('opencomputers:material35') // Transistor

furnace.removeByOutput(ore('oc:materialCircuitBoardPrinted')) // Printed circuit board

/**
 * Floppy Disk assembler recipes
 * Getting floppy disks with specific data is kinda weird. You need to repeatedly use shapeless crafts to cycle through them.
 * Using an assembler with circuit numbers streamlines it.
 */
/**
 * TODO: It's ugly in JEI rn, you don't see that you can input any kind of floppy disk to it. Find a way to show that you
 * can Transform any floppy disk to another one. And make the recipe show as usages of those other recipes
 */

// List of the item stacks with NBT of each program - Order is list they appear in in JEI
// TODO: Reorder this alphabetically, it appears at random in JEI
List<ItemStack> programs = [
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'openloader'], 'oc:lootFactory':
		'opencomputers:openloader', 'oc:color': 13, 'display': ['Name': 'OpenLoader (Boot Loader)']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'plan9k'], 'oc:lootFactory':
		'opencomputers:plan9k', 'oc:color': 1, 'display': ['Name': 'Plan9k (Operating System)']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'data'], 'oc:lootFactory':
		'opencomputers:data', 'oc:color': 9, 'display': ['Name': 'Data Card Software']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'generator'], 'oc:lootFactory':
		'opencomputers:generator', 'oc:color': 5, 'display': ['Name': 'Generator Upgrade Software']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'maze'], 'oc:lootFactory':
		'opencomputers:maze', 'oc:color': 14, 'display': ['Name': 'Mazer']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'dig'], 'oc:lootFactory':
		'opencomputers:dig', 'oc:color': 3, 'display': ['Name': 'Digger']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'oppm'], 'oc:lootFactory':
		'opencomputers:oppm', 'oc:color': 6, 'display': ['Name': 'OPPM (Package Manager)']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'builder'], 'oc:lootFactory':
		'opencomputers:builder', 'oc:color': 11, 'display': ['Name': 'Builder']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'openos'], 'oc:lootFactory':
		'opencomputers:openos', 'oc:color': 2, 'display': ['Name': 'OpenOS (Operating System)']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'openloader'], 'oc:lootFactory':
		'opencomputers:openloader', 'oc:color': 13, 'display': ['Name': 'OpenLoader (Boot Loader)']]),
	item('opencomputers:storage', 1).withNbt(['oc:data': ['oc:fs.label': 'builder'], 'oc:lootFactory':
		'opencomputers:builder', 'oc:color': 11, 'display': ['Name': 'Builder']])
]

// Remove old recipes
crafting.removeByOutput(ore('oc:floppy'))
// TODO: This doesn't remove the default "wild NBT floppy disk + scrench -> other floppy disk recipe for some reason.

// Making the recipes for each
for (ItemStack program : programs) {
	mods.gregtech.assembler.recipeBuilder()
		.inputWildNBT(ore('oc:floppy'))
		.circuitMeta(programs.indexOf(program))
		.outputs(program)
		.duration(20).EUt(VA[ULV])
		.buildAndRegister()
}

// Resetting to a base floppy disk
mods.gregtech.assembler.recipeBuilder()
	.inputWildNBT(ore('oc:floppy'))
	.circuitMeta(32)
	.outputs(item('opencomputers:storage', 1))
	.duration(20).EUt(VA[ULV])
	.buildAndRegister()
