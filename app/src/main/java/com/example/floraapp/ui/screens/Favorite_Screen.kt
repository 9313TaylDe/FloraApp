package com.example.floraapp.ui.screens

import Recomendations
import Rotas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.repository.PlantaViewModel
import com.example.floraapp.ui.components.AppBottomBar

@Composable
fun Favorite_Screen(
	onHome: () -> Unit,
	onFavorites: () -> Unit,
	onAdd: () -> Unit,
	onSobre: () -> Unit,
	onPerfil: () -> Unit,
	viewModel: PlantaViewModel
) {
	val selecionados = remember { mutableStateListOf<Int>() }
	val selecionadosState = selecionados.toList()
	
	val prod by viewModel.pegarTodosProdutos.collectAsState()
	val filtered = prod.filter { it.favorite }
	
	fun toggleitem(item: Plantas) {
		val id = item.id
		if (selecionados.contains(id)) {
			selecionados.remove(id)
		} else {
			selecionados.add(id)
		}
	}
	
	Scaffold(
		topBar = {
			Row(
				modifier = Modifier.padding(horizontal = 6.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = Icons.Default.ArrowBackIosNew,
					contentDescription = "voltar para a tela home",
					modifier = Modifier.clickable { onHome() },
					
					)
				
				Text("Meus favoritos")
				Spacer(Modifier.weight(1f))
				if (selecionadosState.isNotEmpty()) {
					Row(
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							"Deletar",
							modifier = Modifier.clickable {
								selecionadosState.forEach { selectedId ->
									val item = filtered.find { it.id == selectedId }
									if (item != null) {
										viewModel.Deletar(item)
									}
								}
								selecionados.clear()
							}
						)
						Icon(
							imageVector = Icons.Default.Delete, modifier = Modifier.clickable {
								selecionadosState.forEach { selectedId ->
									val item = filtered.find { it.id == selectedId }
									if (item != null) {
										viewModel.Deletar(item)
									}
								}
								selecionados.clear()
							}, contentDescription = "deeletar de favoritos"
						)
					}
				}
			}
		},
		bottomBar = {
			AppBottomBar(
				currentRoute = Rotas.FAVORITES,
				onHome = onHome,
				onFavorites = onFavorites,
				onAdd = onAdd,
				onSobre = onSobre,
				onPerfil = onPerfil
			)
		},
		modifier = Modifier
			.statusBarsPadding()
			.padding(14.dp)
	) { paddingValues ->
		
		if (filtered.isEmpty()) {
			
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(paddingValues),
				contentAlignment = Alignment.Center
			) {
				Text("Você ainda não tem favoritos ❤️")
			}
			
		} else {
			
			LazyVerticalGrid(
				modifier = Modifier
					.padding(paddingValues)
					.padding(15.dp),
				columns = GridCells.Fixed(2)
			) {
				items(filtered) { favoritos ->
					Recomendations(
						producstList = favoritos,
						onAddToFavorite = {
							toggleitem(favoritos)
						},
						irparaDetails = {}
					)
				}
			}
		}
	}
}