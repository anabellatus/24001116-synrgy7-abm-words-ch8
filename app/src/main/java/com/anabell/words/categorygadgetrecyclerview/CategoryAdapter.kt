package com.anabell.words.categorygadgetrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anabell.words.databinding.ItemGadgetCategoryBinding

class CategoryAdapter(private val categoryAdapterListener: CategoryAdapterListener)
    : ListAdapter<CategoryGadget, CategoryViewHolder>(CategoryDiffUtil())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            itemViewBinding = ItemGadgetCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            categoryAdapterListener =  categoryAdapterListener
        )
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}