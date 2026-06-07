package com.example.floraapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.floraapp.data.ProducstList
import com.example.floraapp.repository.ProductViewModel

@Composable
fun Details_Screen(
	id: Int,
	viewmodel: ProductViewModel,
	voltar: () -> Unit,
	irparaFavorito: () -> Unit,
	irparaAtualizar: () -> Unit
) {
	
	val favorite by rememberSaveable { mutableStateOf(false) }
	val configuration = LocalConfiguration.current
	val screenWidth = configuration.screenWidthDp
	
	
	val cardWidth = when {
		screenWidth < 360 -> 120.dp
		screenWidth < 411 -> 130.dp
		screenWidth < 600 -> 130.dp
		else -> 180.dp
	}
	
	val imageHeight = when {
		screenWidth < 360 -> 90.dp
		screenWidth < 411 -> 100.dp
		screenWidth < 600 -> 110.dp
		else -> 140.dp
	}
	val titleSize = when {
		screenWidth < 360 -> 10.sp
		screenWidth < 411 -> 20.sp
		screenWidth < 600 -> 15.sp
		else -> 25.sp
	}
	val iconsize = when {
		screenWidth < 360 -> 20.dp
		screenWidth < 411 -> 20.dp
		screenWidth < 600 -> 30.dp
		else -> 20.dp
	}
	
	val produto by viewmodel.pegarPoriD(id)
		.collectAsState(initial = null)
	
	
	if (produto == null) {
		CircularProgressIndicator()
		return
	}
	val produtoAtual = produto!!
	
	val imgView = when {
		produtoAtual.imgURI.isNotEmpty() -> produtoAtual.imgURI
		produtoAtual.imgRes != 0 -> produtoAtual.imgRes
		else -> null
	}
	
	
	val newPreco = String.format(
		"%.2f",
		produtoAtual.price
	)
	
	if (produto == null) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
		return
	}
	
	
	Box(
		modifier = Modifier.fillMaxSize()
	) {
		
		AsyncImage(
			model = imgView,
			contentDescription = produto!!.title,
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.55f),
			contentScale = ContentScale.Crop
		)
		
		IconButton(
			onClick = voltar,
			modifier = Modifier
				.padding(20.dp)
				.size(48.dp)
				.background(
					Color.White,
					CircleShape
				)
				.align(Alignment.TopStart)
		) {
			Icon(
				Icons.Default.ArrowBack,
				contentDescription = "Voltar"
			)
		}
		
		IconButton(
			onClick = irparaFavorito,
			modifier = Modifier
				.padding(20.dp)
				.size(48.dp)
				.background(
					Color.White,
					CircleShape
				)
				.align(Alignment.TopEnd)
		) {
			Icon(
				Icons.Default.Favorite,
				contentDescription = "Favorito",
				tint = Color(0xFF2E7D32)
			)
		}
		
		Card(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.58f)
				.align(Alignment.BottomCenter),
			shape = RoundedCornerShape(
				topStart = 32.dp,
				topEnd = 32.dp
			)
		) {
			
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(20.dp)
			) {
				
				Text(
					text = produtoAtual.title,
					fontSize = 28.sp,
					fontWeight = FontWeight.Bold
				)
				
				Spacer(modifier = Modifier.height(12.dp))
				
				Text(
					text = "R$ $newPreco",
					color = Color(0xFF2E7D32),
					fontSize = 30.sp,
					fontWeight = FontWeight.Bold
				)
				
				Spacer(modifier = Modifier.height(20.dp))
				
				Text(
					text = "Sobre a planta",
					fontSize = 20.sp,
					fontWeight = FontWeight.Bold
				)
				
				Spacer(modifier = Modifier.height(8.dp))
				
				Text(
					text = produtoAtual.description,
					fontSize = 16.sp,
					lineHeight = 24.sp
				)
				
				Spacer(modifier = Modifier.weight(1f))
				Row(modifier = Modifier
					.fillMaxWidth()) {
					
					Button(
						onClick = {},
						modifier = Modifier
							.height(60.dp),
						shape = RoundedCornerShape(16.dp),
						colors = ButtonDefaults.buttonColors(
							containerColor = Color(0xFF2E7D32)
						)
					) {
						
						Icon(
							Icons.Default.ShoppingCart,
							contentDescription = null
						)
						
						Spacer(
							modifier = Modifier.width(8.dp)
						)
						Text(
							text = "Adicionar ao carrinho"
						)
						
					}
						Text(
							text = "Editar",
							modifier = Modifier.clickable { irparaAtualizar(
							) }
						)
					
				}
			}
		}
	}
}
