package com.example.floraapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.floraapp.R
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.domain.model.PlantasModel
import com.example.floraapp.ui.colors.DarkGreen

@Composable
fun AddToScreen(
    planta: PlantasModel

    ? = null,
    onBack: () -> Unit,
    onSave: (PlantasModel) -> Unit
) {

    var nomePlanta by rememberSaveable {
        mutableStateOf(planta?.title ?: "")
    }

    var familia by rememberSaveable {
        mutableStateOf(planta?.family ?: "")
    }

    var favorito by rememberSaveable {
        mutableStateOf(planta?.favorite ?: "")
    }

    var categoria by rememberSaveable {
        mutableStateOf(planta?.genus ?: "")
    }


    var imagemSelecionada by remember {
        mutableStateOf<Any?>(
            when {
                planta?.imageUrl?.isNotEmpty() == true ->
                    Uri.parse(planta.imageUrl)

                planta?.imageUrl?.toInt() != 0 ->
                    planta?.imageUrl

                else -> null
            }
        )
    }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        imagemSelecionada = uri
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .padding(14.dp),
        topBar = {
            Row(
                modifier = Modifier.padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "voltar para a tela home",
                    modifier = Modifier.clickable { onBack() },

                    )

                Text(
                    text = "Adicionar planta",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.width(20.dp)
                )
            }
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {

            val isSmallScreen = maxWidth < 360.dp
            val isTablet = maxWidth >= 600.dp

            val imageHeight = when {
                isTablet -> 300.dp
                isSmallScreen -> 140.dp
                else -> 220.dp
            }

            val imageSize = when {
                isTablet -> 220.dp
                isSmallScreen -> 100.dp
                else -> 160.dp
            }

            val titleSize = when {
                isTablet -> 22.sp
                isSmallScreen -> 16.sp
                else -> 19.sp
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = if (isTablet) 32.dp else 16.dp,
                        vertical = 16.dp
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                        .clickable {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                        .drawBehind {

                            val stroke = Stroke(
                                width = 2f,
                                pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(10f, 10f),
                                    0f
                                )
                            )

                            drawRect(
                                color = DarkGreen,
                                style = stroke
                            )
                        }
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    if (imagemSelecionada == null) {

                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = null,
                            modifier = Modifier.size(
                                if (isTablet) 72.dp else 48.dp
                            )
                        )

                        Text(
                            text = "Toque para selecionar uma foto",
                            fontSize = if (isTablet) 18.sp else 14.sp
                        )

                    } else {

                        AsyncImage(
                            model = imagemSelecionada,
                            contentDescription = null,
                            modifier = Modifier.size(imageSize)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Nome da planta",
                    fontSize = titleSize
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nomePlanta,
                    onValueChange = { nomePlanta = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Espécie",
                    fontSize = titleSize
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = familia,
                    onValueChange = { familia = it }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Categoria",
                    fontSize = titleSize
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = categoria,
                    onValueChange = { categoria = it }
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Preço",
                    fontSize = titleSize
                )

//                TextField(
//                    modifier = Modifier.fillMaxWidth(),
//                    value = favorito,
//                    onValueChange = { favorito = it }
//                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Observações",
                    fontSize = titleSize
                )

//				TextField(
//					modifier = Modifier.fillMaxWidth(),
//					value = ,
//					onValueChange = { descricao = it }
//				)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            if (isTablet) 60.dp else 52.dp
                        ),
                    enabled =
                        familia.isNotEmpty() &&
                                categoria.isNotEmpty() &&
                                nomePlanta.isNotEmpty() &&
                                imagemSelecionada != null,
                    onClick = {
                        val newItem = PlantasModel(
                            id = planta?.id ?: 0,
                            title = nomePlanta,
                            favorite = planta?.favorite ?: false,
                            imageUrl = R.drawable.outline_local_florist_24.toString(),
                            family = familia,
                            isCustom = planta?.isCustom ?: false,
                            scientificName = planta?.scientificName ?: ""
                        )

                        onSave(newItem)
                    }
                ) {
                    Text(
                        text = "Salvar",
                        fontSize = if (isTablet) 18.sp else 14.sp
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AddToScreenPreview() {
    AddToScreen(
        onBack = {},
        onSave = {}
    )
}