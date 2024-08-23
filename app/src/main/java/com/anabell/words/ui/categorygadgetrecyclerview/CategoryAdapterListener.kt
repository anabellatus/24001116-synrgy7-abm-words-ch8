package com.anabell.words.ui.categorygadgetrecyclerview

import com.anabell.words.data.model.CategoryGadget


interface CategoryAdapterListener {

    fun onClickCategory(data: CategoryGadget)

}