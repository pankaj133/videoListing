package com.example.videolistingapp.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videolistingapp.databinding.VideoitemBinding
import com.example.videolistingapp.models.Item
import javax.inject.Inject

class YouTubeVideoAdapter
@Inject
constructor() : PagingDataAdapter<Item, YouTubeVideoAdapter.YoutubeViewHolder>(VIDEO_COMPARATOR) {

    inner class YoutubeViewHolder(val binding: VideoitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            if (item != null) {
                Glide.with(holder.itemView).load(item.snippet.thumbnails.high.url)
                    .into(holder.binding.imageview)
                holder.binding.title.text = item.snippet.title
                holder.binding.description.text = item.snippet.description

                holder.itemView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+item.id));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.google.android.youtube");
                    holder.itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val binding =
            VideoitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YoutubeViewHolder(binding)
    }


    companion object {
        private val VIDEO_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item,
            ) = oldItem == newItem
        }
    }
}