// NOMILABS

/*
BOTH DUSTS
Put here any material for which both small and tiny dusts dont serve any purpose at all.
Make sure these dusts are not useful for any recycling recipe.
 */
[	//List Materials here - Ordered by ID
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
].each { var material ->
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustSmall' + material))
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustTiny' + material))
	crafting.removeByInput(metaitem('nomilabs:dustSmall' + material))
	crafting.removeByInput(metaitem('nomilabs:dustTiny' + material))


	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustSmall' + material) * 4, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustTiny' + material) * 9, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + material), metaitem('circuit.integrated').withNbt(['Configuration': 2])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + material), metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
}

/*
SMALL DUSTS
Put here materials that need only their small dust removed but not tiny dust
*/
[	//List Materials here - Ordered by ID
	 'Ardite',
	 'Manyullyn',
	 'CrystalMatrix',
	 'Soularium',
	 'VanadiumPentoxide',
	 'KaptonK',
	 'SodiumMethoxide',
	 'Taranium'
].each { var material ->
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustSmall' + material))
	crafting.removeByInput(metaitem('nomilabs:dustSmall' + material))

	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustSmall' + material) * 4, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + material), metaitem('circuit.integrated').withNbt(['Configuration': 2])], null)
}

/*
TINY DUSTS
Put here materials that need only their tiny dust removed but not small dust
Any material with an ingot form should immediately be excluded because nuggets (maybe remove the nugget?)
*/
[	//List Materials here - Ordered by ID
	'Osmiridium8020'
].each { var material ->
	//Removes the crafting recipe
	mods.jei.ingredient.removeAndHide(metaitem('nomilabs:dustTiny' + material))
	crafting.removeByInput(metaitem('nomilabs:dustTiny' + material))

	//Removes the packager recipes
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dustTiny' + material) * 9, metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
	mods.gregtech.packer.removeByInput(12, [metaitem('nomilabs:dust' + material), metaitem('circuit.integrated').withNbt(['Configuration': 1])], null)
}

