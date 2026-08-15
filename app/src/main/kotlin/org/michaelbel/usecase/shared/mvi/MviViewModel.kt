package org.michaelbel.usecase.shared.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class MviViewModel<I: Intent, M: Model>(
    initialState: M
): CoroutineViewModel() {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<M> = _stateFlow

    protected fun reduce(reducer: (M) -> M) {
        _stateFlow.update(reducer)
    }

    abstract fun dispatch(intent: I)
}
