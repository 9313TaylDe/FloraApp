package com.example.floraapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floraapp.R
import com.example.floraapp.ui.colors.iconsColor

@SuppressLint("DefaultLocale")
@Composable
fun CardProducts(
	products: Products, onlike: () -> Unit
) {
	val newpreco = String.format("%.2f", products.price)
	val favorite by rememberSaveable { mutableStateOf(false) }
	Card(
		modifier = Modifier
			.heightIn(min = 30.dp, max = 100.dp)
			.fillMaxWidth()
	) {
		Row(
			modifier = Modifier,
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Image(
				painter = painterResource(products.img),
				contentDescription = "imagemm do ${products.title}",
				modifier = Modifier
					.fillMaxWidth(0.25f)
					.clip(RoundedCornerShape(10.dp)),
				contentScale = ContentScale.Crop
			)
			Spacer(modifier = Modifier.width(20.dp))
			Row(modifier = Modifier.padding(10.dp),
				verticalAlignment = Alignment.CenterVertically) {
				Column() {
					Text(
						text = products.title,
						fontSize = 16.sp,
						fontWeight = FontWeight.Bold
					)
					Spacer(modifier = Modifier.height(10.dp))
					Text(
						text = "R$$newpreco",
						fontSize = 16.sp,
						color = iconsColor,
						fontWeight = FontWeight.SemiBold
					)
				}
				Spacer(modifier = Modifier.weight(1f))
				Icon(
					imageVector = Icons.Default.FavoriteBorder,
					contentDescription = "Icone de favoritar produto",
					modifier = Modifier.size(30.dp)
				)
			}
		}
	}
}

@Preview
@Composable
fun priv() {
	CardProducts(
		onlike = {},
		products = Products(
			title = "FLORNCA",
			img = R.drawable.jiboiaplanta,
			id = 1,
			price = 12.0f,
			description = "aPenas algo",
			tipo = listOf("Interior", "Ornamental", "Purificadora")
		
		)
	)
}