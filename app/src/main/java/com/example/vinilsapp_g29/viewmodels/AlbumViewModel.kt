package com.example.vinilsapp_g29.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilsapp_g29.models.Album
import com.example.vinilsapp_g29.network.NetworkServiceAdapter
import com.example.vinilsapp_g29.repositories.AlbumRepository
import com.example.vinilsapp_g29.repositories.CollectorsRepository

class AlbumViewModel(application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }


    // se encarga de consultar activamente la información de los modelos con el manejador de
    // peticiones de red y actualizar los LiveData respectivos.
    private fun refreshDataFromNetwork() {
        albumsRepository.refreshData({
            _albums.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    // Factory - la cual extiende de ViewModelProvider.Factory y se encarga de crear la instancia del ViewModel.
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
