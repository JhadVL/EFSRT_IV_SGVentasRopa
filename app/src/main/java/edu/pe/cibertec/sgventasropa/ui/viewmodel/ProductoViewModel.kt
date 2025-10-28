package edu.pe.cibertec.sgventasropa.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.pe.cibertec.sgventasropa.data.model.Producto
import edu.pe.cibertec.sgventasropa.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {
    private val repo = ProductoRepository()
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        cargar()
    }

    fun cargar() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _productos.value = repo.obtenerTodos()
            } catch (e: Exception) {
                _productos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun agregar(producto: Producto, onDone: (() -> Unit)? = null) {
        viewModelScope.launch {
            repo.agregar(producto)
            cargar()
            onDone?.invoke()
        }
    }

    fun actualizar(id: String, datos: Map<String, Any>, onDone: (() -> Unit)? = null) {
        viewModelScope.launch {
            repo.actualizar(id, datos)
            cargar()
            onDone?.invoke()
        }
    }

    fun eliminar(id: String, onDone: (() -> Unit)? = null) {
        viewModelScope.launch {
            repo.eliminar(id)
            cargar()
            onDone?.invoke()
        }
    }
}
