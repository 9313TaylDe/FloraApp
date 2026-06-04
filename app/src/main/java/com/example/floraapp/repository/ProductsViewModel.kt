import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductsViewModel(private val prods: ProductDAO) : ViewModel() {
	val pegarTodos = prods.pegarTodos().stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(),
		initialValue = emptyList<Products>()
	)
	
	fun adicionarProduti(products: Products) {
		viewModelScope.launch {
			prods.inserir(products)
		}
	}
	
	fun deletarProdutto(products: Products) {
		viewModelScope.launch {
			prods.delete(products)
		}
	}
	
	fun atualizarProduto(products: Products) {
		viewModelScope.launch { prods.update(products) }
	}
}