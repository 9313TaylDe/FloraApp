package com.example.floraapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.floraapp.data.AppsDataBases
import com.example.floraapp.data.ProducstList
import com.example.floraapp.repository.ProductFactory
import com.example.floraapp.repository.ProductViewModel
import com.example.floraapp.rotas.Rotas
import com.example.floraapp.ui.screens.AddToScreen
import com.example.floraapp.ui.screens.Home_Screen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		
		val db = Room.databaseBuilder(
			context = applicationContext,
			AppsDataBases::class.java, "db_applications"
		).fallbackToDestructiveMigration().build()
		
		val dao = db.getAll()
		lifecycleScope.launch {
			if (dao.pegarTodos().first().isEmpty()) {
				dao.inserir(
					ProducstList(
						title = "Jiboia",
						description = "Planta resistente",
						price = 29.90f,
						img = null,
						imgresources = R.drawable.jiboiaplanta,
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
						onAdd = {
							navController.navigate(Rotas.ADD_SCREEN)
						},
						onHome = {
							navController.navigate(Rotas.HOME)
						}, onPerfil = {}, onCuidados = {}, onFavoritos = {}
					)
				}
				composable(Rotas.ADD_SCREEN) {
					AddToScreen(
						onBack = {
							navController.popBackStack()
						}, onSave = { add ->
							lifecycleScope.launch {
								dao.inserir(add)
							}
							navController.navigate(Rotas.HOME)
						}
					)
				}
			}
		}
	}
}