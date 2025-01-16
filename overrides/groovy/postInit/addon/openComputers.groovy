// MODS_LOADED: opencomputers
// ^, Makes the script not run as long as Open Computers isn't a mod in the player's instance.

package postInit.addon

import gregtech.api.metatileentity.multiblock.CleanroomType
//import gregtech.api.recipes.RecipeBuilder
//import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder
//import gregtech.api.recipes.builders.SimpleRecipeBuilder
//import gregtech.api.recipes.ingredients.GTRecipeItemInput
//import gregtech.api.recipes.recipeproperties.ResearchProperty
import com.nomiceu.nomilabs.util.LabsModeHelper
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

import static gregtech.api.GTValues.*

/**
 * Remove items with a GT equivalent
 * These items already have an equivalent within GT and should be hidden
 */
// Hide from JEI
mods.jei.ingredient.hide(ore('oc:materialCircuitBoardRaw')) // Raw circuit board
mods.jei.ingredient.hide(ore('oc:materialCircuitBoardPrinted')) // Printed circuit board
mods.jei.ingredient.hide(ore('oc:materialTransistor')) // Transistor
mods.jei.ingredient.hide(ore('oc:materialCuttingWire')) // Cutting Wire
mods.jei.ingredient.hide(item('opencomputers:endstone')) // OC Version of endstone

// Remove Recipes
crafting.remove('opencomputers:material33') // Raw circuit board
crafting.remove('opencomputers:material35') // Transistor
crafting.remove('opencomputers:material54') // Chamelium Default Recipe
crafting.remove('opencomputers:endstone27') // OC Version of endstone

furnace.removeByOutput(ore('oc:materialCircuitBoardPrinted')) // Printed circuit board

/**
 * Add oredict tags to items
 * Some items need new oredict tags so it auto-generates new recipes for it
 */

// Microchips
ore_dict.add('circuitHv', item('opencomputers:material', 7)) // Tier 1 circuit
ore_dict.add('circuitEv', item('opencomputers:material', 8)) // Tier 2 circuit
ore_dict.add('circuitIv', item('opencomputers:material', 9)) // Tier 3 circuit

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
		.circuitMeta(programs.indexOf(program) + 1)
		.outputs(program)
		.duration(20).EUt(VA[LV])
		.buildAndRegister()
}

// Resetting to a base floppy disk
mods.gregtech.polarizer.recipeBuilder()
	.inputs(ore('oc:floppy'))
	.outputs(item('opencomputers:storage', 1)) // Floppy Disk
	.duration(60).EUt(VA[LV])
	.buildAndRegister()

/** New content recipes
 * These are the recipes that change the actual content and progression of OC
 */

// Crafting components

// Remove Old
// Microchips
crafting.remove('opencomputers:material36') // Tier 1 Microchip
crafting.remove('opencomputers:material37') // Tier 2 Microchip
crafting.remove('opencomputers:material38') // Tier 3 Microchip

// Keyboard elements
crafting.remove('opencomputers:material43') // Keypad
crafting.remove('opencomputers:material44') // Button Group
crafting.remove('opencomputers:material45') // Arrow Keys

// Make New
// Microchips
for (FluidStack joiningFluid : [fluid('tin') * 1152, fluid('soldering_alloy') * 576]) {
	// Tier 1
	mods.gregtech.circuit_assembler.recipeBuilder()
		.inputs(metaitem('plateSteel') * 8,
			metaitem('plate.nano_central_processing_unit') * 8,
			ore('componentTransistor') * 24,
			metaitem('wireFineRedAlloy') * 32
		)
		.fluidInputs(joiningFluid)
		.outputs(item('opencomputers:material', 7) * 8)
		.duration(200).EUt(VA[HV])
		.buildAndRegister()

	// Tier 2
	mods.gregtech.circuit_assembler.recipeBuilder()
		.inputs(metaitem('plateAluminium') * 16,
			metaitem('plate.qbit_central_processing_unit') * 16,
			metaitem('plate.nano_central_processing_unit') * 32,
			metaitem('component.smd.transistor') * 48,
			metaitem('wireFineTantalum') * 48
		)
		.fluidInputs(joiningFluid)
		.outputs(item('opencomputers:material', 8) * 16)
		.cleanroom(CleanroomType.CLEANROOM)
		.duration(200).EUt(VA[EV])
		.buildAndRegister()

	// Tier 3
	mods.gregtech.circuit_assembler.recipeBuilder()
		.inputs(metaitem('plateStainlessSteel') * 32,
			metaitem('crystal.central_processing_unit') * 8,
			metaitem('plate.qbit_central_processing_unit') * 24,
			metaitem('plate.nano_central_processing_unit') * 48,
			metaitem('component.advanced_smd.transistor') * 16,
			metaitem('wireFineIndiumTinBariumTitaniumCuprate') * 64
		)
		.fluidInputs(joiningFluid)
		.outputs(item('opencomputers:material', 9) * 32)
		.cleanroom(CleanroomType.CLEANROOM)
		.duration(200).EUt(VA[IV])
		.buildAndRegister()
}

// Keyboard elements
for (FluidStack plasticFluid : [fluid('plastic'), fluid('polyvinyl_chloride')]) {
	// Keypad
	mods.gregtech.assembler.recipeBuilder()
		.inputs(item('minecraft:stone_button') * 9, metaitem('springSteel') * 9)
		.fluidInputs(plasticFluid * 1296)
		.circuitMeta(9)
		.outputs(item('opencomputers:material', 16)) // Keypad
		.duration(100).EUt(VA[MV])
		.buildAndRegister()

	// Button Group
	mods.gregtech.assembler.recipeBuilder()
		.inputs(item('minecraft:stone_button') * 6, metaitem('springSteel') * 6)
		.fluidInputs(plasticFluid * 864)
		.circuitMeta(6)
		.outputs(item('opencomputers:material', 15)) // Button Group
		.duration(100).EUt(VA[MV])
		.buildAndRegister()

	// Arrow Keys
	mods.gregtech.assembler.recipeBuilder()
		.inputs(item('minecraft:stone_button') * 5, metaitem('springSteel') * 5)
		.fluidInputs(plasticFluid * 720)
		.circuitMeta(5)
		.outputs(item('opencomputers:material', 14)) // Arrow Keys
		.duration(100).EUt(VA[MV])
		.buildAndRegister()

}

// Chamelium
for (ItemStack printFilaments : [metaitem('nomilabs:wireFinePolylacticAcid') * 16,
								 metaitem('nomilabs:wireFineNylon') * 8,
								 metaitem('nomilabs:wireFinePeek') * 4
]) {
	mods.gregtech.compressor.recipeBuilder()
		.inputs(printFilaments)
		.outputs(item('opencomputers:material', 28)) // Chamelium
		.duration(180).EUt(VA[MV])
		.buildAndRegister()
}


/**
 * PLA Line
 */

// Carbonated Biomass Broth
mods.gregtech.mixer.recipeBuilder()
	.inputs(metaitem('dustCalcite') * 8)
	.fluidInputs(fluid('biomass') * 8000)
	.circuitMeta(1)
	.fluidOutputs(fluid('carbonated_biomass_broth') * 16000)
	.duration(100).EUt(VA[MV])
	.buildAndRegister()

// Fermented Biomass Broth
mods.gregtech.fermenter.recipeBuilder()
	.fluidInputs(fluid('carbonated_biomass_broth') * 1000)
	.fluidOutputs(fluid('fermented_biomass_broth') * 1250)
	.duration(400).EUt(VA[HV])
	.buildAndRegister()

// Crude Lactic Acid
mods.gregtech.centrifuge.recipeBuilder()
	.fluidInputs(fluid('fermented_biomass_broth') * 2500)
	.fluidOutputs(fluid('crude_lactic_acid') * 1500)
	.outputs(metaitem('dustCalcite'))
	.duration(300).EUt(VA[MV])
	.buildAndRegister()

// Lactic Acid
mods.gregtech.distillery.recipeBuilder()
	.fluidInputs(fluid('crude_lactic_acid') * 1500)
	.fluidOutputs(fluid('lactic_acid') * 1000)
	.duration(140).EUt(VA[MV])
	.buildAndRegister()

// PLA
mods.gregtech.chemical_reactor.recipeBuilder()
	.fluidInputs(fluid('lactic_acid') * 1000, fluid('sulfuric_acid') * 2000)
	.fluidOutputs(fluid('polylactic_acid') * 1000, fluid('diluted_sulfuric_acid') * 3000)
	.duration(220).EUt(VA[MV])
	.buildAndRegister()

/**
 * Nylon Line
 */
// TODO: Remove these comments
// Nylon :

/**
 * PEEK Line
 */

// TODO: Remove these comments
// PEEK : C19H12O3
