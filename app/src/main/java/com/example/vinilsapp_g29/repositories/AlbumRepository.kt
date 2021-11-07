package com.example.vinilsapp_g29.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilsapp_g29.models.Album
import com.example.vinilsapp_g29.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}