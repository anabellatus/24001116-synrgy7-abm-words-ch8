package com.anabell.words.domain

import com.anabell.words.ui.gadgetrecycler.Gadget

interface GadgetRepository {

    fun fetchData(): List<Gadget>
}