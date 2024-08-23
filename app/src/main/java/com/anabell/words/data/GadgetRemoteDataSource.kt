package com.anabell.words.data

import com.anabell.words.data.model.Gadget

interface GadgetRemoteDataSource {

    fun fetchData(): List<Gadget>
}