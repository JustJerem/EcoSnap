package com.jeremieguillot.ecosnap

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Cleaning : Route

    @Serializable
    data class Submit(val id: Long) : Route

    @Serializable
    data object PictureScreen : Route
}