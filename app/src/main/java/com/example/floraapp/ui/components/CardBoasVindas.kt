package com.example.floraapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.ui.colors.DarkGreen
import com.example.floraapp.ui.colors.White

@Composable
fun BoasVindas(
	title: String, textColor: Color, textWeight:  TextUnit
) {
	Column(
		modifier = Modifier.height(60.dp).background(White)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				color = textColor,
				fontSize = textWeight,
				text = "Olá, $title!", modifier = Modifier.height(
					20
						.dp
				)
			)
			Icon(
				imageVector = Icons.Default.Eco, contentDescription = "",
				tint = Color.Green, modifier = Modifier.size(18.dp)
			)
		}
		Text(
			fontSize = 10.sp,
			text = "Que bom te ver por aqui!"
		)
	}
}