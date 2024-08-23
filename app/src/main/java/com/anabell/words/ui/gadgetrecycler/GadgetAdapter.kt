package com.anabell.words.ui.gadgetrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anabell.words.data.model.Gadget
import com.anabell.words.databinding.ItemGadgetBinding

class GadgetAdapter(private val gadgetAdapterListener: GadgetAdapterListener) :
    ListAdapter<Gadget, GadgetViewHolder>(GadgetDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GadgetViewHolder {
        return GadgetViewHolder(
            itemViewBinding = ItemGadgetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            gadgetAdapterListener = gadgetAdapterListener
        )
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: GadgetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}