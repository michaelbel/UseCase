package org.michaelbel.usecase.shared.coroutines.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.michaelbel.usecase.shared.coroutines.SharedDispatchers
import org.michaelbel.usecase.shared.coroutines.impl.SharedDispatchersImpl

@Module
@InstallIn(SingletonComponent::class)
interface DispatchersModule {

    @Binds
    fun sharedDispatchers(impl: SharedDispatchersImpl): SharedDispatchers
}

