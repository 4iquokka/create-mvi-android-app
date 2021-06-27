package com.shinyj.template.mvi.ui.main

import com.shinyj.template.mvi.domain.state.DataState
import com.shinyj.template.mvi.domain.state.StateEvent
import com.shinyj.template.mvi.ui.common.BaseViewModel
import com.shinyj.template.mvi.ui.main.state.MainStateEvent.CreateStateMessageEvent
import com.shinyj.template.mvi.ui.main.state.MainStateEvent.DoSomething
import com.shinyj.template.mvi.ui.main.state.MainViewState
import kotlinx.coroutines.flow.Flow

class MainViewModel : BaseViewModel<MainViewState>() {
    override fun handleNewData(data: MainViewState) {
        data.let { viewState ->
            viewState.someString?.let { string ->
                setSomeString(string)
            }
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<MainViewState>?> = when (stateEvent) {

            is DoSomething -> {
                TODO("Add the implementation of DoSomething")
            }

            is CreateStateMessageEvent -> {
                emitStateMessageEvent(
                    stateMessage = stateEvent.stateMessage,
                    stateEvent = stateEvent
                )
            }

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): MainViewState = MainViewState()

    fun setSomeString(string: String) {
        val update = getViewState()
        update.someString = string
        setViewState(update)
    }
}