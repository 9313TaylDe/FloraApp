package com.example.floraapp.ui.screens

import Recomendations
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.R
import com.example.floraapp.repository.ProductViewModel
import com.example.floraapp.rotas.Rotas
import com.example.floraapp.ui.colors.fundocardsuggestions
import com.example.floraapp.ui.colors.iconsColor
import com.example.floraapp.ui.colors.textFieldBorderColor
import com.example.floraapp.ui.colors.titleColor
import com.example.floraapp.ui.components.BoasVindas


@Composable
fun Home_Screen(
	productViewModel: ProductViewModel,
	onDetails: (Int) -> Unit,
	onAdd: () -> Unit,
	onHome: () -> Unit,
	onFavorites: () -> Unit,
	onCuidados: () -> Unit,
	onPerfil: () -> Unit
) {
	
	val produtos by productViewModel.pegarTodosProdutos
		.collectAsState()
	
	var searching by remember { mutableStateOf("") }
	
	val categorias = listOf("Internas", "Externas", "Suculentas", "Flores")
	
	fun iconeDoBotao(rota: String) = when (rota) {
		Rotas.HOME -> Icons.Default.Home
		Rotas.FAVORITES -> Icons.Default.Favorite
		Rotas.CUIDADOS -> Icons.Default.Eco
		Rotas.PROFILE -> Icons.Default.Person
		else -> Icons.Default.Add
	}
	
	fun categoriaIcon(cat: String) = when (cat) {
		"Internas" -> R.drawable.icon1
		"Externas" -> R.drawable.icon2
		"Suculentas" -> R.drawable.icon3
		else -> R.drawable.outline_local_florist_24
	}
	
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(modifier = Modifier.weight(1f)) {
					BoasVindas(
						title = "Expedito",
						textColor = titleColor,
						textWeight = 17.sp
					)
				}
				
				Icon(
					imageVector = Icons.Default.NotificationsNone,
					contentDescription = null,
					modifier = Modifier.size(28.dp)
				)
			}
		},
		
		bottomBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 20.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				
				listOf(
					Rotas.HOME,
					Rotas.FAVORITES,
					Rotas.ADD,
					Rotas.CUIDADOS,
					Rotas.PROFILE
				).forEach { rota ->
					
					Button(
						colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
						onClick = {
							when (rota) {
								Rotas.HOME -> onHome()
								Rotas.FAVORITES -> onFavorites()
								Rotas.ADD -> onAdd()
								Rotas.CUIDADOS -> onCuidados()
								Rotas.PROFILE -> onPerfil()
							}
						}
					) {
						Icon(
							imageVector = iconeDoBotao(rota),
							contentDescription = null,
							tint = Color.Black
						)
					}
				}
			}
		}
	) { padding ->
		
		Column(
			modifier = Modifier
				.padding(padding)
				.fillMaxSize()
				.padding(10.dp)
		) {
			
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.border(1.dp, textFieldBorderColor, RoundedCornerShape(15.dp))
					.padding(8.dp)
			) {
				TextField(
					value = searching,
					onValueChange = { searching = it },
					placeholder = { Text("Buscar plantas") },
					colors = TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						disabledContainerColor = Color.Transparent,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent
					),
					modifier = Modifier.fillMaxWidth()
				)
			}
			
			Spacer(modifier = Modifier.height(12.dp))
			
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(150.dp)
					.background(fundocardsuggestions, RoundedCornerShape(25.dp))
					.padding(12.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(modifier = Modifier.weight(1f)) {
					Text(
						"Plantas transformam ambientes",
						color = Color.White,
						fontSize = 18.sp
					)
					Text(
						"Veja nossas sugestões",
						color = Color.White,
						fontSize = 12.sp
					)
				}
				
				Image(
					painter = painterResource(R.drawable.sugggestionsplantcards),
					contentDescription = null,
					modifier = Modifier.size(120.dp),
					contentScale = ContentScale.Crop
				)
			}
			
			Spacer(modifier = Modifier.height(20.dp))
			
			Row(verticalAlignment = Alignment.CenterVertically) {
				Text("Categorias", fontWeight = FontWeight.Bold, fontSize = 18.sp)
				Spacer(modifier = Modifier.weight(1f))
				Text("Ver todos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
			}
			LazyRow {
				items(categorias) { cat ->
					Column(
						modifier = Modifier
							.padding(6.dp)
							.size(80.dp)
							.background(Color.White, RoundedCornerShape(10.dp))
							.border(1.dp, iconsColor, RoundedCornerShape(10.dp)),
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center
					) {
						Icon(
							painter = painterResource(categoriaIcon(cat)),
							contentDescription = null,
							tint = iconsColor,
							modifier = Modifier.size(30.dp)
						)
						Text(cat, fontSize = 10.sp)
					}
				}
			}
			
			Spacer(modifier = Modifier.height(20.dp))
			
			val filtered = produtos.filter {
				it.title.contains(searching, ignoreCase = true)
			}
			
			if (filtered.isEmpty()) {
				Text("Nenhum produto para exibir")
			} else {
				
				LazyVerticalGrid(
					columns = GridCells.Fixed(3),
					modifier = Modifier
						.fillMaxWidth()
						.height(800.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp),
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					items(
						items = filtered,
						key = { it.id }
					) { produto ->
						
						Recomendations(
							producstList = produto,
							irparaDetails = onDetails,
							onAddToFavorite = { planta ->
								productViewModel.Atualizar(
									planta.copy(
										favorite = !planta.favorite
									)
								)
							}
						)
					}
				}
			}
		}
	}
}