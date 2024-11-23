/*
Small Dusts - Nomilabs
Put here any small dusts to remove that dont serve any purpose at all.
Make sure these dusts are not useful for any recycling recipe.
If a dust here is an output or an input of any recipe, it is a bug and should get reported
*/
for (var smallToRemove : [
	//List Materials here - Ordered by ID
	//Nomilabs
	'Ardite',
	'Manyullyn',
	'CrystalMatrix',
	'Soularium',
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
	'VanadiumPentoxide',
	'AmmoniumMetavanadate',
	'PhthalicAnhydride',
	'Ethylanthraquinone',
	'GrapheneOxide',
	'KaptonK',
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
	'SodiumMethoxide',
	'StoneResidue',
	'UncommonResidue',
	'OxidisedResidue',
	'RefinedResidue',
	'Taranium',
	'Darmstadtite',
	'Dulysite',
	'Laurite',
	'Cuprorhodsite'
])
{
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustSmall' + smallToRemove))
	crafting.removeByInput(metaitem('nomilabs:dustSmall' + smallToRemove))

	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustSmall' + smallToRemove) * 4, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + smallToRemove), metaitem('circuit.integrated').withNbt(['Configuration': 2])], null)
}

/*
Tiny Dusts - Nomilabs
Same as above but for tiny dusts.
Any material with an ingot form should immediately be excluded because nuggets (maybe remove the nugget?)
*/
for (var tinyToRemove : [
	//List Materials here - Ordered by ID
	//Nomilabs
	'Osmiridium8020',
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
])
{
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustTiny' + tinyToRemove))
	crafting.removeByInput(metaitem('nomilabs:dustTiny' + tinyToRemove))

	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustTiny' + tinyToRemove) * 9, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + tinyToRemove), metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)

}

