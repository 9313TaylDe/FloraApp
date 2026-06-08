package com.example.floraapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.ui.components.AppBottomBar
@Composable
fun Sobre_Screen(
	onHome: () -> Unit,
	onAdd: () -> Unit,
	onSobre: () -> Unit,
	onFavorites: () -> Unit,
	onPerfil: () -> Unit
) {
	
	val primaryColor = Color(0xFF2C5E3B)
	val textFieldBorderColor = Color(0xFFE0E0E0)
	
	Scaffold(
		bottomBar = {
			AppBottomBar(
				currentRoute = Rotas.SOBRE,
				onHome = onHome,
				onFavorites = onFavorites,
				onAdd = onAdd,
				onSobre = onSobre,
				onPerfil = onPerfil
			)
		}
	) { padding ->
		
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(padding) // 👈 importante: usa o padding do Scaffold
				.padding(20.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			
			Spacer(modifier = Modifier.height(32.dp))
			
			Box(
				modifier = Modifier
					.size(80.dp)
					.clip(RoundedCornerShape(20.dp))
					.background(primaryColor),
				contentAlignment = Alignment.Center
			) {
				Icon(
					imageVector = Icons.Default.Info,
					contentDescription = null,
					tint = Color.White,
					modifier = Modifier.size(40.dp)
				)
			}
			
			Spacer(modifier = Modifier.height(16.dp))
			
			Text(
				text = "FloraApp",
				fontSize = 24.sp,
				fontWeight = FontWeight.Bold,
				color = primaryColor
			)
			
			Text(
				text = "Versão 1.0.0",
				fontSize = 14.sp,
				color = Color.Gray
			)
			
			Spacer(modifier = Modifier.height(32.dp))
			
			Text(
				text = "Este aplicativo foi desenvolvido como parte de um projeto focado em usabilidade e performance...",
				fontSize = 15.sp,
				color = Color(0xFF333333),
				textAlign = TextAlign.Justify,
				lineHeight = 22.sp
			)
			
			Spacer(modifier = Modifier.weight(1f))
			
			Text(
				text = "© 2026 com.example.floraapp",
				fontSize = 11.sp,
				color = Color.LightGray
			)
		}
	}
}