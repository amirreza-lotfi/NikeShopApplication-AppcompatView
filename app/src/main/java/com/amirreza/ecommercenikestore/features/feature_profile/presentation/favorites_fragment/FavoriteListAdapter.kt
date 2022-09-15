package com.amirreza.ecommercenikestore.features.feature_profile.presentation.favorites_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amirreza.ecommercenikestore.databinding.ItemFavoriteBinding
import com.amirreza.ecommercenikestore.features.feature_store.domain.entity.Product
import com.amirreza.ecommercenikestore.features.feature_store.domain.repository.ImageLoaderI

class FavoriteListAdapter(
    private val products: MutableList<Product>,
    private val events: FavoriteEvents,
    private val imageLoader:ImageLoaderI
) : RecyclerView.Adapter<FavoriteListAdapter.ItemHolder>() {

    inner class ItemHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.favoriteProductTv.text = product.title
            imageLoader.load(binding.imageOfProduct,product.image)

            itemView.setOnClickListener {
                events.onClick(product)
            }
            itemView.setOnLongClickListener{
                products.remove(product)
                notifyItemRemoved(adapterPosition)
                events.onLongClick(product,products.size)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}