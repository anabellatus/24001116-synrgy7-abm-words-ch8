package com.anabell.words.ui.gadgetrecycler

import com.anabell.words.data.model.Gadget

interface GadgetAdapterListener {

    fun onClickGadget(data: Gadget)

}