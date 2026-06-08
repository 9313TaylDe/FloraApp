package com.example.floraapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.floraapp.data.AppsDataBases
import com.example.floraapp.data.ProducstList
import com.example.floraapp.repository.ProductFactory
import com.example.floraapp.repository.ProductViewModel
import com.example.floraapp.ui.screens.AddToScreen
import com.example.floraapp.ui.screens.Details_Screen
import com.example.floraapp.ui.screens.Favorite_Screen
import com.example.floraapp.ui.screens.Home_Screen
import com.example.floraapp.ui.screens.Profile_Screen
import com.example.floraapp.ui.screens.Sobre_Screen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		
		val db = Room.databaseBuilder(
			applicationContext,
			AppsDataBases::class.java,
			"db_applications"
		).fallbackToDestructiveMigration().build()
		
		val dao = db.getAll()
		
		lifecycleScope.launch {
			if (dao.pegarTodos().first().isEmpty()) {
				dao.inserir(
					ProducstList(
						title = "Jiboia",
						description = "Planta resistente",
						price = 29.90f,
						imgRes = R.drawable.jiboiaplanta,
						typeMedia = ""
					)
				)
			}
		}
		
		setContent {
			
			val viewmodel: ProductViewModel = viewModel(
				factory = ProductFactory(dao)
			)
			
			val navController = rememberNavController()
			
			NavHost(
				navController = navController,
				startDestination = Rotas.HOME
			) {
				
				composable(Rotas.HOME) {
					Home_Screen(
						productViewModel = viewmodel,
						
						onDetails = { id ->
							navController.navigate("${Rotas.DETAILS}/$id")
						},
						
						onAdd = {
							navController.navigate(Rotas.ADD)
						},
						
						onHome = {
							navController.navigate(Rotas.HOME)
						},
						
						onFavorites = {
							navController.navigate(Rotas.FAVORITES)
						},
						onPerfil = {
							navController.navigate(Rotas.PROFILE)
						},
						
						onSobre = {
							navController.navigate(Rotas.SOBRE)
						}
					)
				}
				
				composable(Rotas.ADD) {
					AddToScreen(
						onBack = { navController.popBackStack() },
						onSave = { add ->
							lifecycleScope.launch {
								dao.inserir(add)
							}
							navController.navigate(Rotas.HOME)
						}
					)
				}
				

				composable(Rotas.FAVORITES) {
					Favorite_Screen(
						onHome = { navController.navigate(Rotas.HOME) },
						onAdd = { navController.navigate(Rotas.ADD) },
						onSobre = { navController.navigate(Rotas.SOBRE) },
						onFavorites = { navController.navigate(Rotas.FAVORITES) },
						onPerfil = { navController.navigate(Rotas.PROFILE) },
						viewModel = viewmodel
					)
				}
				
				
				composable(Rotas.DETAILS_ID) { backStackEntry ->
					
					val id = backStackEntry.arguments
						?.getString("id")
						?.toIntOrNull() ?: 0
					
					Details_Screen(
						id = id,
						viewmodel = viewmodel,
						voltar = {
							navController.popBackStack()
						},
						irparaFavorito = {},
						irparaAtualizar = {
							navController.navigate("add_screen/$id")
							
						}
					)
				}
				composable(Rotas.ADD_EDIT) { backStackEntry ->
					val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
					
					val produto by viewmodel.pegarPoriD(id).collectAsState(initial = null)
					produto?.let {
						
						AddToScreen(
							produto = it,
							onBack = {
								navController.popBackStack()
							},
							onSave = { produtoAtualizado ->
								
								viewmodel.Atualizar(produtoAtualizado)
								
								navController.popBackStack()
							}
						)
					}
				}
				
				composable(Rotas.SOBRE) {
					Sobre_Screen(
						onHome = { navController.navigate(Rotas.HOME) },
						onAdd = { navController.navigate(Rotas.ADD) },
						onSobre = { navController.navigate(Rotas.SOBRE) },
						onFavorites = {
							navController.navigate(Rotas.FAVORITES)
						},
						onPerfil = { navController.navigate(Rotas.PROFILE) }
					)
				}
				
				composable(Rotas.PROFILE) {
					Profile_Screen(
						onHome = { navController.navigate(Rotas.HOME) },
						onAdd = { navController.navigate(Rotas.ADD) },
						onSobre = { navController.navigate(Rotas.SOBRE) },
						onFavorites = {
							navController.navigate(Rotas.FAVORITES)
						}, onPerfil = { navController.navigate(Rotas.PROFILE) }
					)
				}
			}
		}
	}
}