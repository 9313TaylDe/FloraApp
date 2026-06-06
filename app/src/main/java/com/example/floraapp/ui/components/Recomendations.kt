import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.floraapp.data.ProducstList

@Composable
fun Recomendations(
	producstList: ProducstList
) {
	
	val newPrice = String.format("%.2f", producstList.price)
	val imgDecides = producstList.imgresources ?: producstList.img
	
	val configuration = LocalConfiguration.current
	val screenWidth = configuration.screenWidthDp
	
	val cardWidth = when {
		screenWidth < 360 -> 120.dp
		screenWidth < 411 -> 130.dp
		screenWidth < 600 -> 140.dp
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
		screenWidth < 411 -> 11.sp
		screenWidth < 600 -> 12.sp
		else -> 14.sp
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
			.padding(6.dp)
	) {
		
		Column {
			
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(imageHeight)
			) {
				
				AsyncImage(
					model = imgDecides,
					contentDescription = producstList.title,
					modifier = Modifier
						.fillMaxSize()
						.clip(
							RoundedCornerShape(
								topStart = 12.dp,
								topEnd = 12.dp
							)
						),
					contentScale = ContentScale.Crop
				)
				
				Row(
					modifier = Modifier
						.align(Alignment.TopEnd)
						.padding(6.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						imageVector = Icons.Default.Eco,
						contentDescription = null
					)
					
					Text(
						text = producstList.typeMedia
					)
				}
			}
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				
				Column(
					modifier = Modifier.weight(1f),
				) {
					
					Text(
						text = producstList.title,
						fontSize = titleSize,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						fontWeight = FontWeight.Bold
					)
					
					Text(
						text = producstList.description, fontSize = titleSize,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						fontWeight = FontWeight.SemiBold
					)
					
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = "R$ $newPrice",
							fontSize = titleSize
						)
						Icon(
							imageVector = if(producstList.favorite) Icons.Default.FavoriteBorder else Icons.Default.FavoriteBorder,
							contentDescription = "Favoritar",
							modifier = Modifier.size(iconsize)
						)
					}
					
				}
			}
			
		}
	}
}


@Preview(
	name = "Galaxy A07",
	showBackground = true,
	widthDp = 360,
	heightDp = 800
)
@Composable
fun PreviewA07() {
	Recomendations(
		producstList = ProducstList(
			id = 1,
			title = "Rosa Vermelha",
			price = 29.90f,
			description = "espiro que desce como fogo",
			typeMedia = "Flores",
			img = "https://picsum.photos/300/300"
		)
	)
}

@Preview(
	name = "Celular Grande",
	showBackground = true,
	widthDp = 411,
	heightDp = 891
)
@Composable
fun PreviewPhone() {
	Recomendations(
		producstList = ProducstList(
			id = 1,
			title = "Orquídea Premium",
			price = 49.90f,
			description = "vem como em ppenttecostes",
			typeMedia = "Flores",
			img = "https://picsum.photos/300/300"
		)
	)
}

@Preview(
	name = "Tablet",
	showBackground = true,
	widthDp = 800,
	heightDp = 1280
)
@Composable
fun PreviewTablet() {
	Recomendations(
		producstList = ProducstList(
			id = 1,
			title = "Samambaia Gigante",
			price = 89.90f,
			description = "e enche-me de noov",
			typeMedia = "Flores",
			img = "https://picsum.photos/300/300"
		)
	)
}
