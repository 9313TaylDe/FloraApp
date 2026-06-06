package com.example.floraapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.floraapp.R
import com.example.floraapp.data.ProducstList

@SuppressLint("DefaultLocale")
@Composable
fun CardProducts(
	producstList: ProducstList,
	onlike: () -> Unit
) {
	val imageModel = if (producstList.imgresources != null) {
		producstList.img
	} else {
		producstList.imgresources
	}
	val newpreco = String.format("%.2f", producstList.price)
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
	Card(
		modifier = Modifier
			.width(cardWidth)
			.height(200.dp)
			.padding(6.dp)
	) {
		val newPrice = String.format("%.2f", producstList.price)
		val imadecides = producstList.imgresources ?: producstList.img
		Column() {
			Box(
				contentAlignment = Alignment.CenterStart
			) {
				AsyncImage(
					model = imadecides, contentDescription = ""
				)
				Row(
					modifier = Modifier.align(Alignment.TopEnd).zIndex(zIndex = 1f),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						imageVector = Icons.Default.Eco,
						contentDescription = producstList.typeMedia
					)
					Text(
						text = producstList.typeMedia
					)
				}
			}
			Column() {
				Text(
					text = producstList.title,
					fontSize = titleSize,
					fontWeight = FontWeight.SemiBold,
				)
				Text(
					text = producstList.description,
					fontSize = titleSize,
					fontWeight = FontWeight.SemiBold,
				)
				Row() {
					Text(
						text = newpreco,
						fontSize = titleSize,
						fontWeight = FontWeight.SemiBold,
					)
					Icon(
						contentDescription = "Icone de favoritar",
						imageVector = if (producstList.favorite)
							Icons.Default.Favorite else Icons.Default.Favorite
					)
				}
			}
		}
	}
}

@Preview
@Composable
fun priv() {
	CardProducts(
		onlike = {},
		producstList = ProducstList(
			title = "FLORNCA",
			img = null,
			imgresources = R.drawable.jiboiaplanta,
			id = 1,
			price = 12.0f,
			description = "aPenas algo",
			typeMedia = "flor"
		)
	)
}