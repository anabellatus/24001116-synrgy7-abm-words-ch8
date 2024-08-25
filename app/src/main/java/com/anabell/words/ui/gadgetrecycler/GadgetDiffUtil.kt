package com.anabell.words.ui.gadgetrecycler

import androidx.recyclerview.widget.DiffUtil
import com.anabell.words.domain.model.Gadget

class GadgetDiffUtil : DiffUtil.ItemCallback<Gadget>() {

    override fun areItemsTheSame(oldItem: Gadget, newItem: Gadget): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Gadget, newItem: Gadget): Boolean {
        return oldItem.image == newItem.image
    }
}