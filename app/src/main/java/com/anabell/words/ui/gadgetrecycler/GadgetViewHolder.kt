package com.anabell.words.ui.gadgetrecycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anabell.words.domain.model.Gadget
import com.anabell.words.databinding.ItemGadgetBinding

class GadgetViewHolder(
    private val gadgetAdapterListener: GadgetAdapterListener,
    private val itemViewBinding: ItemGadgetBinding,
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    fun bind(data: Gadget) {
        itemViewBinding.tvProductName.text = data.name
        itemViewBinding.tvProductPrice.text = "Rp. ${data.price} ,-"
        itemViewBinding.imageProduct.load(data.image)
        itemViewBinding.root.setOnClickListener {
            gadgetAdapterListener.onClickGadget(data)
        }

//        val isFavorite = data.isFavorite
//
//        if (isFavorite) {
//            itemViewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
//        } else {
//            itemViewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
//        }
//
//        itemViewBinding.favoriteButton.setOnClickListener {
//            if (isFavorite) {
//                Log.d("GadgetViewHolder", "Remove from favorite")
//                gadgetAdapterListener.onRemoveFromFavorite(data)
//                itemViewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
//            } else {
//                gadgetAdapterListener.onAddToFavorite(data)
//                itemViewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
//            }
//        }

    }

}