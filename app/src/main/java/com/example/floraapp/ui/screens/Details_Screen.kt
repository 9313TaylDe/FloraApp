package com.example.floraapp.ui.screens

import Recomendations
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.floraapp.R
import com.example.floraapp.domain.model.PlantasModel
import com.example.floraapp.repository.PlantaViewModel

@Composable
fun Details_Screen(
    id: Int,
    plantaViewModel: PlantaViewModel,
    voltar: () -> Unit,
    irparaFavorito: () -> Unit,
    onAddFavorito: (PlantasModel) -> Unit,
    irparaAtualizar: () -> Unit
) {
    val message by plantaViewModel.message.collectAsState()
    val pegarTodosPlantas by plantaViewModel.pegarTodasAsPlantas.collectAsState()
    val planta by plantaViewModel.pegarPoriD(id)
        .collectAsState(initial = null)

    val context = LocalContext.current

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()

            plantaViewModel.limparMessage()
        }
    }

    if (planta == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val plantaAtual = planta!!

    val imagem: Any = plantaAtual.imageUrl
        ?.takeIf { it.isNotEmpty() }
        ?.let { Uri.parse(it) }
        ?: R.drawable.outline_local_florist_24

    Surface(
        modifier = Modifier
            .statusBarsPadding()
            .padding(),
    ) {

        Box(
            modifier = Modifier
                .padding()
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                IconButton(
                    onClick = voltar,
                    modifier = Modifier
                        .offset(x = 10.dp, y = 10.dp)
                        .zIndex(10f)
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color.Black.copy(alpha = 0.35f))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Voltar para a tela home",
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier
                            .padding(14.dp)
                            .size(22.dp)
                    )
                }
                AsyncImage(
                    model = imagem,
                    contentDescription = plantaAtual.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.55f),
                    contentScale = ContentScale.Crop
                )

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
                            plantaAtual.title,
                            fontSize = 28.sp
                        )
                        Spacer(Modifier.weight(1f))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(pegarTodosPlantas.take(10)) { planta ->
                                Recomendations(
                                    plantasModel = planta,
                                    irparaDetails = {},
                                    onAddToFavorite = {}
                                )
                            }
                        }

                        Spacer(Modifier.weight(1f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Button(
                                onClick = {
                                    onAddFavorito(
                                        plantaAtual.copy(
                                            favorite = !plantaAtual.favorite
                                        )
                                    )
                                },
                                modifier = Modifier.height(60.dp)
                            ) {
                                Icon(
                                    imageVector = if (plantaAtual.favorite) {
                                        Icons.Default.Favorite
                                    } else {
                                        Icons.Default.FavoriteBorder
                                    },
                                    contentDescription = "Favoritar planta"
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = {
                                    irparaAtualizar()
                                },
                                modifier = Modifier.height(60.dp)
                            ) {
                                Text("Editar")
                            }
                        }
                    }
                }
            }
        }
    }
}
