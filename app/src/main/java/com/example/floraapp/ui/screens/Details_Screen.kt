
package com.example.floraapp.ui.screens

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .padding(14.dp),

        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Voltar para a tela home",
                    modifier = Modifier.clickable {
                        voltar()
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text("Detalhes da planta")
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f)
            ) {

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
