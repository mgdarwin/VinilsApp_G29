package com.example.vinyls_jetpack_application.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vinyls_jetpack_application.R
import com.example.vinyls_jetpack_application.databinding.AlbumListItemBinding
import com.example.vinyls_jetpack_application.models.Album
import com.example.vinyls_jetpack_application.ui.AlbumListFragmentDirections

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album : Album = albums.get(position)
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(albums[position].albumId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
            println(action)
        }

        /**
        Glide.with(mContext)
            .load(album.cover)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(holder.viewDataBinding.albumImg)
        */
    }
    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_list_item
        }
    }


/**
    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val viewDataBinding = AlbumListItemBinding.bind(view)

        }
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_list_item

        }
    */
}
