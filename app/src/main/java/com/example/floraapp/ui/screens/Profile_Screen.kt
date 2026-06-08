package com.example.floraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.ui.components.AppBottomBar
import com.example.floraapp.ui.components.ProfileOptionItem
@Composable
fun Profile_Screen(
	onHome: () -> Unit,
	onAdd: () -> Unit,
	onSobre: () -> Unit,
	onFavorites: () -> Unit,
	onPerfil: () -> Unit
) {
	
	val primaryGreen = Color(0xFF2C5E3B)
	val borderColor = Color(0xFFE0E0E0)
	
	Scaffold(
		bottomBar = {
			AppBottomBar(
				currentRoute = Rotas.PROFILE,
				onHome = onHome,
				onFavorites = onFavorites,
				onAdd = onAdd,
				onSobre = onSobre,
				onPerfil = onPerfil
			)
		}
	) { innerPadding ->
		
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding) // 👈 importante
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			
			Spacer(modifier = Modifier.height(24.dp))
			
			Box(
				modifier = Modifier
					.size(120.dp)
					.border(2.dp, primaryGreen, CircleShape)
					.padding(4.dp)
			) {
				Image(
					painter = painterResource(id = android.R.drawable.ic_menu_gallery),
					contentDescription = null,
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Crop
				)
			}
			
			Spacer(modifier = Modifier.height(12.dp))
			
			Text("Expedito", fontSize = 22.sp, fontWeight = FontWeight.Bold)
			Text("expedito@email.com", fontSize = 14.sp, color = Color.Gray)
			
			Spacer(modifier = Modifier.height(32.dp))
			
			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				
				ProfileOptionItem(
					title = "Editar Dados",
					icon = Icons.Default.PersonOutline,
					borderColor = borderColor
				)
				
				ProfileOptionItem(
					title = "Favoritos",
					icon = Icons.Default.FavoriteBorder,
					borderColor = borderColor
				)
				
				ProfileOptionItem(
					title = "Notificações",
					icon = Icons.Default.NotificationsNone,
					borderColor = borderColor
				)
				
				ProfileOptionItem(
					title = "Sair",
					icon = Icons.Default.ExitToApp,
					borderColor = borderColor,
					contentColor = Color.Red
				)
			}
		}
	}
}