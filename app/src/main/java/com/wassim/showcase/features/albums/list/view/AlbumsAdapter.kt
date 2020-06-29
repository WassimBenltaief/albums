package com.wassim.showcase.features.albums.list.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.GrayscaleTransformation
import com.wassim.showcase.R
import com.wassim.showcase.features.albums.AlbumUiModel
import com.wassim.showcase.utils.inflate
import com.wassim.showcase.utils.observer
import com.wassim.showcase.utils.onClickWithThrottle
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumsAdapter(
    val imageLoader: ImageLoader,
    val onItemClicked: ((album: AlbumUiModel) -> Unit)
) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<AlbumUiModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = albums.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        parent.context.inflate(parent, R.layout.album_item)
    )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    inner class AlbumViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(albumDomainModel: AlbumUiModel) {
            with(itemView) {
                imageLoader.execute(
                    LoadRequest.Builder(context)
                        .data(albumDomainModel.coverUrl)
                        .target(coverImage)
                        .crossfade(true)
                        .crossfade(1000)
                        .error(R.drawable.ic_image)
                        .transformations(GrayscaleTransformation())
                        .build()
                )
                onClickWithThrottle(600L) {
                    onItemClicked.invoke(albumDomainModel)
                }
                title.text = albumDomainModel.name
                artist.text = albumDomainModel.artist
            }
        }
    }
}
