package com.example.floraapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileOptionItem
@Composable
fun ProfileOptionItem(
	title: String,
	icon: ImageVector,
	borderColor: Color,
	contentColor: Color = Color.Black
) {
	
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(56.dp)
			.border(1.dp, borderColor, RoundedCornerShape(12.dp))
			.clickable { }
			.padding(horizontal = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		
		Row(verticalAlignment = Alignment.CenterVertically) {
			
			Icon(
				imageVector = icon,
				contentDescription = null,
				tint = contentColor,
				modifier = Modifier.size(22.dp)
			)
			
			Spacer(modifier = Modifier.width(12.dp))
			
			Text(
				text = title,
				fontSize = 16.sp,
				color = contentColor
			)
		}
		
		Icon(
			imageVector = Icons.Default.ChevronRight,
			contentDescription = null,
			tint = Color.Gray
		)
	}
}