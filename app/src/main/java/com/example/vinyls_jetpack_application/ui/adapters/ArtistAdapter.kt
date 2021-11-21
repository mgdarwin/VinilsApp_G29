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
import com.example.vinyls_jetpack_application.databinding.ArtistListItemBinding
import com.example.vinyls_jetpack_application.models.Artist
import com.example.vinyls_jetpack_application.ui.AlbumListFragmentDirections
import com.example.vinyls_jetpack_application.ui.MainActivity

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private lateinit var mContext: Context

    class ArtistViewHolder(val viewDataBinding: ArtistListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_list_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist : Artist = artists.get(position)
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(artists[position].artistId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
            println(action)
        }

        with(holder) {
            Glide.with(mContext)
                .load(artist.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(viewDataBinding.artistImg)
        }


    }




    override fun getItemCount(): Int {
        return artists.size
    }
}
