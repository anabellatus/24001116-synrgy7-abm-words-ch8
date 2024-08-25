package com.anabell.words.ui.categorygadgetrecyclerview

import com.anabell.words.domain.model.CategoryGadget


interface CategoryAdapterListener {

    fun onClickCategory(data: CategoryGadget)

}