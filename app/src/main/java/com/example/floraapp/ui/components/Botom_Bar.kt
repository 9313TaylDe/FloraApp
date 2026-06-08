package com.example.floraapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBottomBar(
	currentRoute: String,
	onHome: () -> Unit,
	onFavorites: () -> Unit,
	onAdd: () -> Unit,
	onSobre: () -> Unit,
	onPerfil: () -> Unit
) {
	
	fun icon(rota: String) = when (rota) {
		Rotas.HOME -> Icons.Default.Home
		Rotas.FAVORITES -> Icons.Default.Favorite
		Rotas.ADD -> Icons.Default.Add
		Rotas.SOBRE -> Icons.Default.Info
		Rotas.PROFILE -> Icons.Default.Person
		else -> Icons.Default.Home
	}
	
	fun isSelected(rota: String): Boolean {
		return currentRoute == rota || currentRoute.startsWith(rota)
	}
	
	val items = listOf(
		Rotas.HOME,
		Rotas.FAVORITES,
		Rotas.ADD,
		Rotas.SOBRE,
		Rotas.PROFILE
	)
	
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 16.dp),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		
		items.forEach { rota ->
			
			val onClick = when (rota) {
				Rotas.HOME -> onHome
				Rotas.FAVORITES -> onFavorites
				Rotas.ADD -> onAdd
				Rotas.SOBRE -> onSobre
				Rotas.PROFILE -> onPerfil
				else -> ({})
			}
			
			val selected = isSelected(rota)
			
			IconButton(onClick = onClick) {
				Icon(
					imageVector = icon(rota),
					contentDescription = rota,
					tint = if (selected)
						Color(0xFF2C5E3B)
					else
						Color.Gray
				)
			}
		}
	}
}