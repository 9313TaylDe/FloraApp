package com.example.floraapp.ui.components

import com.example.floraapp.domain.model.PlantasModel

fun SuggestionsCard(): List<PlantasModel> {
	return listOf(
		PlantasModel(
			title = "Jiboia",
			scientificName = "Epipremnum aureum",
			family = "Araceae",
			genus = "Epipremnum",
			description = "A Jiboia é uma das plantas ornamentais mais populares para ambientes internos. Possui folhas verdes brilhantes e é muito fácil de cuidar.",
			imageUrl = null,
			isCustom = false,
			favorite = false
		),

		PlantasModel(
			title = "Espada-de-São-Jorge",
			scientificName = "Dracaena trifasciata",
			family = "Asparagaceae",
			genus = "Dracaena",
			description = "A Espada-de-São-Jorge é uma planta resistente, conhecida por suas folhas alongadas e pela facilidade de manutenção. É muito utilizada na decoração de ambientes internos.",
			imageUrl = null,
			isCustom = false,
			favorite = false
		),

		PlantasModel(
			title = "Costela-de-Adão",
			scientificName = "Monstera deliciosa",
			family = "Araceae",
			genus = "Monstera",
			description = "A Costela-de-Adão é uma planta tropical conhecida por suas folhas grandes e recortadas. É bastante utilizada na decoração de ambientes internos.",
			imageUrl = null,
			isCustom = false,
			favorite = false
		),

		PlantasModel(
			title = "Lírio-da-Paz",
			scientificName = "Spathiphyllum wallisii",
			family = "Araceae",
			genus = "Spathiphyllum",
			description = "O Lírio-da-Paz possui folhas verdes e flores brancas elegantes. É uma ótima opção para decorar ambientes internos.",
			imageUrl = null,
			isCustom = false,
			favorite = false
		),

		PlantasModel(
			title = "Zamioculca",
			scientificName = "Zamioculcas zamiifolia",
			family = "Araceae",
			genus = "Zamioculcas",
			description = "A Zamioculca é conhecida por sua resistência e facilidade de manutenção. Possui folhas verdes brilhantes e se adapta bem a ambientes internos.",
			imageUrl = null,
			isCustom = false,
			favorite = false
		)
	)
}