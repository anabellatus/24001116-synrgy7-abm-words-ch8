package com.anabell.words.ui.categorygadgetrecyclerview

import androidx.recyclerview.widget.DiffUtil
import com.anabell.words.data.model.CategoryGadget

class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryGadget>() {

    override fun areItemsTheSame(oldItem: CategoryGadget, newItem: CategoryGadget): Boolean {
        return oldItem.icon == newItem.icon
    }

    override fun areContentsTheSame(oldItem: CategoryGadget, newItem: CategoryGadget): Boolean {
        return oldItem.icon == newItem.icon
    }

}