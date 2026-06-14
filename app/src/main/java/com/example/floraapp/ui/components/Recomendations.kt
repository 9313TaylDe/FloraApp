import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.floraapp.R
import com.example.floraapp.data.ProducstList
import com.example.floraapp.ui.colors.iconsColor

@Composable
fun Recomendations(
	producstList: ProducstList,
	irparaDetails: (Int) -> Unit,
	onAddToFavorite: (ProducstList) -> Unit
) {
	
	val precoFormatado = String.format("%.2f", producstList.price)
	
	val imageModel = when {
		producstList.imgURI.isNotEmpty() ->
			Uri.parse(producstList.imgURI)
		
		producstList.imgRes != 0 ->
			producstList.imgRes
		
		else ->
			R.drawable.outline_local_florist_24
	}
	
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
	
	Card(
		modifier = Modifier
			.width(cardWidth)
			.padding(6.dp)
			.clickable {
				irparaDetails(producstList.id)
			},
		shape = RoundedCornerShape(16.dp)
	) {
		Column {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(imageHeight)
			) {
				AsyncImage(
					model = imageModel,
					contentDescription = producstList.title,
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Crop
				)
				Row(
					modifier = Modifier
						.align(Alignment.TopStart)
						.padding(8.dp)
						.background(
							Color(0xAA4CAF50),
							RoundedCornerShape(20.dp)
						)
						.padding(
							horizontal = 8.dp,
							vertical = 4.dp
						),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						imageVector = Icons.Default.Eco,
						contentDescription = null,
						tint = Color.White,
						modifier = Modifier.size(14.dp)
					)
					Spacer(
						modifier = Modifier.width(4.dp)
					)
					Text(
						text = producstList.typeMedia,
						color = Color.White,
						fontSize = 10.sp,
						fontWeight = FontWeight.Bold
					)
				}
			}
			Column(
				modifier = Modifier.padding(10.dp)
			) {
				Text(
					text = producstList.title,
					fontSize = (titleSize.value + 1).sp,
					fontWeight = FontWeight.Bold,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
				Spacer(
					modifier = Modifier.height(4.dp)
				)
				Text(
					text = producstList.description,
					fontSize = (titleSize.value - 1).sp,
					color = Color.Gray,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis
				)
				Spacer(
					modifier = Modifier.height(8.dp)
				)
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = "R$ $precoFormatado",
						fontSize = titleSize,
						fontWeight = FontWeight.Bold,
						color = Color(0xFF2E7D32)
					)
					Icon(
						imageVector = if (producstList.favorite)
							Icons.Default.Favorite
						else
							Icons.Default.FavoriteBorder,
						contentDescription = "Favorito",
						tint = iconsColor,
						modifier = Modifier
							.size(20.dp)
							.clickable {
								onAddToFavorite(producstList)
							}
					)
				}
			}
		}
	}
}