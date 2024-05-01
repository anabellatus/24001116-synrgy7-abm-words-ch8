package com.anabell.words.categorygadgetrecyclerview

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffUtil: DiffUtil.ItemCallback<CategoryGadget>() {

    override fun areItemsTheSame(oldItem: CategoryGadget, newItem: CategoryGadget): Boolean {
        return oldItem.icon == newItem.icon
    }

    override fun areContentsTheSame(oldItem: CategoryGadget, newItem: CategoryGadget): Boolean {
        return oldItem.icon == newItem.icon
    }

}