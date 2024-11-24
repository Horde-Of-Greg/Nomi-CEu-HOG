// NOMILABS

def bothLabs = [
	//List Materials here - Ordered by ID
	'Iridosmine8020',
	'Kaemanite',
	'TungstenTrioxide',
	'NiobiumPentoxide',
	'TantalumPentoxide',
	'Fluorite',
	'ManganeseDifluoride',
	'MolybdenumTrioxide',
	'LeadChloride',
	'Wollastonite',
	'SodiumMetavanadate',
	'AmmoniumMetavanadate',
	'PhthalicAnhydride',
	'Ethylanthraquinone',
	'GrapheneOxide',
	'Durene',
	'PyromelliticDianhydride',
	'Oxydianiline',
	'NaquadahOxide',
	'Pyromorphite',
	'NaquadahHydroxide',
	'Snowchestite',
	'CaesiumHydroxide',
	'LeadMetasilicate',
	'PlatinumMetallic',
	'PalladiumMetallic',
	'AmmoniumHexachloroplatinate',
	'PotassiumBisulfate',
	'PotassiumPyrosulfate',
	'PotassiumSulfate',
	'ZincSulfate',
	'SodiumNitrate',
	'RhodiumNitrate',
	'SodiumRuthenate',
	'SodiumPeroxide',
	'IridiumDioxideResidue',
	'AmmoniumHexachloroiridiate',
	'CrudePlatinumResidue',
	'CrudePalladiumResidue',
	'IridiumGroupSludge',
	'CrudeRhodiumResidue',
	'RhodiumSalt',
	'StoneResidue',
	'UncommonResidue',
	'OxidisedResidue',
	'RefinedResidue',
	'Darmstadtite',
	'Dulysite',
	'Laurite',
	'Cuprorhodsite'
]

def smallLabs = [
	//List Materials here - Ordered by ID
	'Ardite',
	'Manyullyn',
	'CrystalMatrix',
	'Soularium',
	'VanadiumPentoxide',
	'KaptonK',
	'SodiumMethoxide',
	'Taranium'
]

def tinyLabs = [
	//List Materials here - Ordered by ID
	'Osmiridium8020'
]

// Define the method to remove dusts
public static void yeetDusts(String size, String modPrefix, String material) {
	def circuitMap = [
		'Tiny': 1,
		'Small': 2
	]
	def amountMap = [
		'Tiny': 9,
		'Small': 4
	]
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem(modPrefix + 'dust' + size + material))
	crafting.removeByInput(metaitem(modPrefix + 'dust' + size + material))

	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem(modPrefix + 'dust' + size + material) * amountMap[size], metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem(modPrefix + 'dust' + material), metaitem('circuit.integrated').withNbt(['Configuration': circuitMap[size]])], null)
}

// Execute the methods for each list
for (material in bothLabs) {
	yeetDusts('Small', 'nomilabs:', material)
	yeetDusts('Tiny', 'nomilabs:', material)
}
for (material in smallLabs) {
	yeetDusts('Small', 'nomilabs:', material)
}
for (material in tinyLabs) {
	yeetDusts('Tiny', 'nomilabs:', material)
}
