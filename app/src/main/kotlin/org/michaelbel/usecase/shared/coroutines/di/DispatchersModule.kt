package org.michaelbel.usecase.shared.coroutines.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.coroutines.impl.AppDispatchersImpl

@Module
@InstallIn(SingletonComponent::class)
interface DispatchersModule {

    @Binds
    fun appDispatchers(impl: AppDispatchersImpl): AppDispatchers
}
