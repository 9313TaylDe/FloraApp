package com.example.floraapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Categorys() {
	val tipos = listOf(
		"Interior",
		"Exterior",
		"Tropical",
		"Suculenta",
		"Flor",
		"Purificadora",
		"Medicinal",
		"Frutífera",
		"Ornamental"
	)
	Column() {
		Row() {
			Text(
				text = ("Categorias")
			)
			Spacer(modifier = Modifier.weight(1f))
			Text(
				text = ("Ver todas")
			)
		}
		Row() {
		
		}
	}
}