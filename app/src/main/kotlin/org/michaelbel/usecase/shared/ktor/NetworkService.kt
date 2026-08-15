package org.michaelbel.usecase.shared.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import org.michaelbel.usecase.shared.ktor.response.RandomDogImageResponse

class NetworkService @Inject constructor(
    private val ktorHttpClient: HttpClient
) {

    suspend fun randomDogImage(): RandomDogImageResponse {
        return ktorHttpClient.get(RANDOM_DOG_IMAGE_URL).body()
    }

    private companion object {
        const val RANDOM_DOG_IMAGE_URL = "https://dog.ceo/api/breeds/image/random"
    }
}
