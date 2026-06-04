package com.example.floraapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.floraapp.R

@Composable
fun Recomendations(
	products: Products
) {
	val newprice = String.format("%.2f", products.price)
	Card(
	
	) {
		Card(
			modifier = Modifier
				.width(110.dp)
				.height(220.dp)
		) {
			Column() {
				Box(
					modifier = Modifier
						.width(110.dp)
						.height(150.dp)
				) {
					
					Image(
						painter = painterResource(products.img),
						contentDescription = null,
						modifier = Modifier
							.fillMaxSize()
							.clip(RoundedCornerShape(10.dp)),
						contentScale = ContentScale.Crop
					)
					
					Icon(
						imageVector = Icons.Default.FavoriteBorder,
						contentDescription = null,
						modifier = Modifier
							.align(Alignment.BottomEnd)
							.offset(x = 3.dp, y = 3.dp)
							.padding(8.dp)
					)
				}
				
				Text(
					text = products.title
				)
				Text(
					text = "R$$newprice"
				)
			}
		}
		Products(
			title = "FLORNCA",
			img = R.drawable.jiboiaplanta,
			id = 1,
			price = 12.0f,
			description = "aPenas algo",
			tipo = listOf("Interior", "Ornamental", "Purificadora")
		
		)
		Products(
			title = "FLORNCA",
			img = R.drawable.jiboiaplanta,
			id = 1,
			price = 12.0f,
			description = "aPenas algo",
			tipo = listOf("Interior", "Ornamental", "Purificadora")
		
		)
		Products(
			title = "FLORNCA",
			img = R.drawable.jiboiaplanta,
			id = 1,
			price = 12.0f,
			description = "aPenas algo",
			tipo = listOf("Interior", "Ornamental", "Purificadora")
		
		)
	}
}