package com.anabell.words.data.datasource.remote.retrofit

import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.model.Gadget
import retrofit2.http.GET

interface GadgetService {

    @GET("gadgets")
    suspend fun getGadgets(): List<Gadget>

    @GET("categories")
    suspend fun getCategories(): List<CategoryGadget>

}