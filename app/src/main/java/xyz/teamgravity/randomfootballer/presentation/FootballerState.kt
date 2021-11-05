package xyz.teamgravity.randomfootballer.presentation

import xyz.teamgravity.randomfootballer.domain.model.FootballerModel

data class FootballerState(
    val footballer: FootballerModel? = null,
    val loading: Boolean = false,
    val error: String? = null
)
