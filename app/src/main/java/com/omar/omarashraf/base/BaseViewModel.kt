package com.omar.omarashraf.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _state: MutableStateFlow<BaseState> =
        MutableStateFlow(IdleStat())
    val state: StateFlow<BaseState> = _state

    private val intents: MutableSharedFlow<BaseIntent> = MutableSharedFlow()

    init {
        listenOnIntents()
    }

    private fun listenOnIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                handleIntent(intent)
            }
        }
    }

    abstract suspend fun handleIntent(intent: BaseIntent)

    fun addAction(intent: BaseIntent) {
        viewModelScope.launch {
            intents.emit(intent)
        }

    }

    protected fun emit(state: BaseState) {
        _state.tryEmit(state)
    }
}