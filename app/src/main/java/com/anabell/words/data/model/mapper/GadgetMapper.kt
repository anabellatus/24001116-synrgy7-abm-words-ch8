package com.anabell.words.data.model.mapper

import com.anabell.words.data.datasource.local.room.GadgetEntity
import com.anabell.words.data.model.Gadget

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