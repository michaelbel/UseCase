package org.michaelbel.usecase.sample03_Ktor

import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.ktor.NetworkService
import org.michaelbel.usecase.shared.usecase.UseCase
import javax.inject.Inject

class FetchRandomDogImageUseCase @Inject constructor(
    private val networkService: NetworkService,
    dispatchers: AppDispatchers
): UseCase<Unit, String>(dispatchers.io) {

    override suspend fun execute(params: Unit): String {
        return try {
            networkService.randomDogImage().message
        } catch (e: Exception) {
            throw FetchImageException(e.message.orEmpty().ifEmpty { "Случилась ошибка" })
        }
    }

    data class FetchImageException(
        override val message: String
    ): Exception(message)
}
