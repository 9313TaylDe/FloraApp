package com.example.floraapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.floraapp.repository.ProductViewModel

@Composable
fun Details_Screen(
	id: Int,
	viewmodel: ProductViewModel,
	voltar: () -> Unit,
	irparaFavorito: () -> Unit,
	irparaAtualizar: () -> Unit
) {
	
	val produto by viewmodel.pegarPoriD(id)
		.collectAsState(initial = null)
	
	if (produto == null) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
		return
	}
	
	val produtoAtual = produto!!
	
	val imgView = when {
		produtoAtual.imgURI.isNotEmpty() -> produtoAtual.imgURI
		produtoAtual.imgRes != 0 -> produtoAtual.imgRes
		else -> null
	}
	
	val newPreco = String.format("%.2f", produtoAtual.price)
	
	Scaffold(
		modifier = Modifier
			.statusBarsPadding()
			.padding(14.dp),
		topBar = {
			Row(
				modifier = Modifier.padding(horizontal = 6.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = Icons.Default.ArrowBackIosNew,
					contentDescription = "voltar para a tela home",
					modifier = Modifier.clickable { voltar() },
					
					)
				Text(
					"Detalhes do produto"
				)
			}
		}
	) { paddingValues ->
		
		Box(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			AsyncImage(
				model = imgView,
				contentDescription = produtoAtual.title,
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight(0.55f),
				contentScale = ContentScale.Crop
			)
			
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight(0.58f)
					.align(Alignment.BottomCenter),
				shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
			) {
				
				Column(
					modifier = Modifier
						.fillMaxSize()
						.padding(20.dp)
				) {
					
					Text(produtoAtual.title, fontSize = 28.sp)
					
					Text("R$ $newPreco", color = Color(0xFF2E7D32))
					
					Spacer(Modifier.weight(1f))
					
					Row(horizontalArrangement = Arrangement.SpaceBetween) {
						
						Button(
							onClick = {},
							modifier = Modifier.height(60.dp)
						) {
							Icon(Icons.Default.ShoppingCart, null)
							Spacer(Modifier.width(8.dp))
							Text("Carrinho")
						}
						
						Spacer(Modifier.width(10.dp))
						
						Button(
							onClick = {},
							modifier = Modifier.height(60.dp)
						) {
							Text(
								text = "Editar",
								modifier = Modifier.clickable {
									irparaAtualizar()
								}
							)
						}
					}
				}
			}
		}
	}
}
