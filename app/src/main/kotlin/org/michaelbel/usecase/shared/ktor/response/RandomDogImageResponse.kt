package org.michaelbel.usecase.shared.ktor.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomDogImageResponse(
    @SerialName("message") val message: String,
    @SerialName("status") val status: String
)
