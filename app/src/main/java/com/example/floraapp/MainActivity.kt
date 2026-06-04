package com.example.floraapp

import ProductsViewModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.floraapp.rotas.Rotas
import com.example.floraapp.ui.screens.Home_Screen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		
		val db = Room.databaseBuilder(
			context = applicationContext,
			AppDataBase::class.java, "db_application"
		).fallbackToDestructiveMigration().build()
		
		val dao = db.getAll()
		lifecycleScope.launch {
			if(dao.pegarTodos().first().isEmpty()){
				dao.inserir(
					Products(
						title = "Jiboia",
						description = "Planta resistente",
						price = 29.90f,
						img = R.drawable.jiboiaplanta,
						tipo = listOf("Interior")
					)
				)
				
			}
		}
		setContent {
		
			
			val viewmodel: ProductsViewModel = viewModel(
				factory = ProductViewModelFactory(dao)
			)
			val navController = rememberNavController()
		
			
			NavHost(
				navController = navController,
				startDestination = Rotas.HOME
			) {
				composable(Rotas.HOME) {
					Home_Screen(
						productsViewModel = viewmodel
					)
				}
			}
		}
	}
}