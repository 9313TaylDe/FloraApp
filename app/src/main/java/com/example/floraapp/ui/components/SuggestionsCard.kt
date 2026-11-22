package com.example.floraapp.ui.components

import com.example.floraapp.R
import com.example.floraapp.data.entitys.Plantas

fun listaSuggestions(): List<Plantas> {
	return listOf(
		Plantas(
			title = "Jiboia",
			description = "A Jiboia é uma das plantas ornamentais mais populares para ambientes internos. Possui folhas verdes brilhantes e é muito fácil de cuidar.",
			price = 39.90f,
			imgRes = R.drawable.jiboiaplanta,
			typeMedia = ("Purificadora")
		),
		
		Plantas(
			title = "São Jorge",
			description = "A Zamioculca é conhecida por sua resistência e facilidade de manutenção.",
			price = 49.90f,
			imgRes = R.drawable.jiboiaplanta,
			typeMedia = ("Purificadora")
		),
		
		Plantas(
			title = "Espada-de-São-Jorge",
			description = "A Espada-de-São-Jorge é uma planta resistente e muito utilizada em ambientes internos.",
			price = 29.90f,
			imgRes = R.drawable.jiboiaplanta,
			typeMedia = ("Purificadora")
		),
		
		Plantas(
			title = "Costela-de-Adão",
			description = "A Costela-de-Adão é uma planta tropical exuberante com folhas grandes e recortadas.",
			price = 59.90f,
			imgRes = R.drawable.jiboiaplanta,
			typeMedia = ("Purificadora")
		),
		
		Plantas(
			title = "Lírio-da-Paz",
			description = "O Lírio-da-Paz possui flores brancas elegantes e é ótimo para ambientes internos.",
			price = 44.90f,
			imgRes = R.drawable.jiboiaplanta,
			typeMedia = ("Purificadora")
		)
	)
}