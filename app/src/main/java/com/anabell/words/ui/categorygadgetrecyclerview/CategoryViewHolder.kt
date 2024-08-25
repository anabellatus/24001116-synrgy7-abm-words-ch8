package com.anabell.words.ui.categorygadgetrecyclerview

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.databinding.ItemGadgetCategoryBinding

class CategoryViewHolder(
    private val categoryAdapterListener: CategoryAdapterListener,
    private val itemViewBinding: ItemGadgetCategoryBinding,
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    fun bind(data: CategoryGadget) {
        itemViewBinding.tvProductName.text = data.name
        itemViewBinding.imageProduct.load(data.icon)
        itemViewBinding.root.setOnClickListener {
            categoryAdapterListener.onClickCategory(data)
        }
    }

}