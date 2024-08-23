package com.anabell.words.ui.gadgetrecycler

import com.anabell.words.data.model.Gadget

interface GadgetAdapterListener {

    fun onClickGadget(data: Gadget)
//    fun onAddToFavorite(data: Gadget)
//    fun onRemoveFromFavorite(data: Gadget)

}