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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.R
import com.example.floraapp.repository.ProductViewModel
import com.example.floraapp.ui.colors.fundocardsuggestions
import com.example.floraapp.ui.colors.iconsColor
import com.example.floraapp.ui.colors.textFieldBorderColor
import com.example.floraapp.ui.colors.titleColor
import com.example.floraapp.ui.components.BoasVindas

@Composable
fun Home_Screen(
	productViewModel: ProductViewModel? = null,
	onHome: () -> Unit = {},
	onFavoritos: () -> Unit = {},
	onAdd: () -> Unit = {},
	onCuidados: () -> Unit = {},
	onPerfil: () -> Unit = {}
) {
	val botoes = listOf(
		"HOME",
		"FAVORITES",
		"ADD",
		"CUIDADOS",
		"PERFIL"
	)
	
	
	fun iconeDoBotao(botao: String) = when (botao) {
		"HOME" -> Icons.Default.Home
		"FAVORITES" -> Icons.Default.Favorite
		"CUIDADOS" -> Icons.Default.Eco
		"PERFIL" -> Icons.Default.Person
		else -> Icons.Default.Add
	}
	
	val categorias = listOf(
		"Internas",
		"Externas",
		"Suculentas",
		"Flores",
		
		)
	
	fun categoriasIcons(categoria: String): Int {
		return when (categoria) {
			"Internas" -> R.drawable.icon1
			"Externas" -> R.drawable.icon2
			"Suculenttas" -> R.drawable.icon3
			"Flores" -> R.drawable.outline_local_florist_24
			else -> R.drawable.icon4
		}
		
	}
	
	val pprodutos = productViewModel
		?.pegarTodosProdutos
		?.collectAsState()
		?.value
		?: emptyList()
	
	
	var searching by remember { mutableStateOf("") }
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.statusBarsPadding(),
		topBar = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(
					modifier = Modifier
						.weight(1f)
						.height(30.dp)
				) {
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
				botoes.forEach { bootos ->
					Button(
						colors = ButtonDefaults.buttonColors(
							containerColor = Color.Transparent
						),
						modifier = Modifier.background(Color.Transparent),
						onClick = {
							when (bootos) {
								"HOME" -> onHome()
								"ADD" -> onAdd()
								"FAVORITES" -> onFavoritos()
								"CUIDADOS" -> onCuidados()
								"Perfil" -> onPerfil()
							}
						}
					) {
						Icon(
							imageVector = iconeDoBotao(bootos),
							contentDescription = "", tint = Color.Black
						)
						
					}
				}
			}
		}
	) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.verticalScroll(rememberScrollState())
				.padding(10.dp)
				.fillMaxSize()
		) {
			Box(
				contentAlignment = Alignment.CenterStart,
				modifier = Modifier
					.fillMaxWidth()
					.border(
						1.dp,
						textFieldBorderColor,
						RoundedCornerShape(15.dp)
					)
			) {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = "Ícone busca",
					tint = iconsColor,
					modifier = Modifier
						.padding(start = 5.dp)
						.size(30.dp)
				)
				TextField(
					colors = TextFieldDefaults.colors(
						focusedContainerColor = Color.Transparent,
						unfocusedContainerColor = Color.Transparent,
						disabledContainerColor = Color.Transparent,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent,
						disabledIndicatorColor = Color.Transparent
					
					),
					modifier = Modifier.padding(start = 22.dp),
					value = searching,
					onValueChange = { searching = it },
					placeholder = { Text("Buscar plantas") }
				)
			}
			Spacer(modifier = Modifier.height(5.dp))
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(150.dp)
					.background(
						fundocardsuggestions,
						RoundedCornerShape(25.dp)
					),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(
					modifier = Modifier
						.padding(15.dp)
						.fillMaxWidth(0.38f)
				) {
					Text(
						text = "Plantas transforman ambientes",
						fontSize = 20.sp, color = Color.White
					)
					Spacer(modifier = Modifier.height(15.dp))
					Row(
						modifier = Modifier
							.padding(5.dp)
							.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = "Veja nossas sugestões",
							fontSize = 11.sp,
							letterSpacing = 1.sp,
							color = Color.White,
							modifier = Modifier.fillMaxWidth(0.67f)
						
						)
						Spacer(modifier = Modifier.width(5.dp))
						
						Icon(
							imageVector = Icons.Default.ArrowCircleRight,
							tint = Color.White,
							modifier = Modifier.size(55.dp),
							contentDescription = "Icone de ver " +
									"sugestoes"
						)
					}
				}
				Image(
					modifier = Modifier
						.size(210.dp)
						.background(Color.Transparent),
					contentScale = ContentScale.Crop,
					painter = painterResource(R.drawable.sugggestionsplantcards),
					contentDescription = null
				)
			}
			Spacer(modifier = Modifier.height(25.dp))
			Column() {
				Row() {
					Text(
						text = "Categorias",
						fontSize = 18.sp,
						fontWeight = FontWeight.Bold,
						color = Color.Black
					)
					Spacer(modifier = Modifier.weight(1f))
					Text(
						text = "Ver todas",
						fontSize = 16.sp,
						fontWeight = FontWeight.Bold,
						color = iconsColor
					)
				}
				Spacer(modifier = Modifier.height(3.dp))
				
				LazyRow(
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					items(categorias) { cattegs ->
						Column(
							modifier = Modifier
								.width(75.dp)
								.height(65.dp)
								.shadow(
									elevation = 8.dp,
									shape = RoundedCornerShape(10.dp),
									ambientColor = Color.Gray,
									spotColor = Color.Gray
								)
								.background(
									color = Color.White,
									shape = RoundedCornerShape(10.dp)
								)
								.border(
									width = 1.dp,
									color = iconsColor,
									shape = RoundedCornerShape(10.dp)
								),
							verticalArrangement = Arrangement.Center,
							horizontalAlignment = Alignment.CenterHorizontally
						) {
							Icon(
								painter = painterResource(categoriasIcons(cattegs)),
								contentDescription = "Icones categorias",
								modifier = Modifier.size(45.dp),
								tint = iconsColor
							)
							Text(
								text = cattegs, fontSize = 10.sp, fontWeight = FontWeight.SemiBold
							)
						}
					}
				}
			}
			
			if (pprodutos.isEmpty()) {
				Text("Nenhum produto para exibir")
			} else {
				LazyRow(
				) {
					items(pprodutos) { prods ->
						Recomendations(
							prods
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
fun prevedond() {
	Home_Screen(
	
	)
	
}