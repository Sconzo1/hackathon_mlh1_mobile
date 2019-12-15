package com.vladco.fudo.model.data

import org.threeten.bp.LocalDate


data class Event(
    val id: String,
    val name: String,
    val date: LocalDate
)
