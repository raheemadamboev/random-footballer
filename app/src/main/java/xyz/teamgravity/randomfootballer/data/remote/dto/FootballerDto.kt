package xyz.teamgravity.randomfootballer.data.remote.dto

import com.google.gson.annotations.SerializedName
import xyz.teamgravity.randomfootballer.domain.model.FootballerModel

data class FootballerDto(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: String
)

fun FootballerDto.toFootballer(): FootballerModel {
    return FootballerModel(
        name = name,
        description = description,
        imageUrl = imageUrl
    )
}
