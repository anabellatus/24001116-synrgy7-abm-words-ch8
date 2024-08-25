package com.anabell.words.ui.gadgetrecycler

import com.anabell.words.domain.model.Gadget

interface GadgetAdapterListener {

    fun onClickGadget(data: Gadget)

}