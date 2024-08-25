package com.anabell.words.data.mapper

import com.anabell.words.data.datasource.local.room.GadgetEntity
import com.anabell.words.domain.model.Gadget

fun Gadget.toGadgetEntity(): GadgetEntity {
    return GadgetEntity(
        id = id,
        name = name,
        price = price,
        category = category,
        image = image
    )
}

fun GadgetEntity.toGadget(): Gadget {
    return Gadget(
        id = id,
        name = name,
        price = price,
        category = category,
        image = image
    )
}

fun List<GadgetEntity>.toGadget(): List<Gadget> {
    return map { gadgetEntity -> gadgetEntity.toGadget() }
}