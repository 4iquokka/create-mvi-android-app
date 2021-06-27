package com.shinyj.template.mvi.ui.main.state

import com.shinyj.template.mvi.domain.state.StateEvent
import com.shinyj.template.mvi.domain.state.StateMessage

sealed class MainStateEvent : StateEvent{

    //TODO:
    class DoSomething() : MainStateEvent() {
        override fun errorInfo(): String = "Error while doing something"

        override fun eventName(): String = "DoSomething"

        override fun shouldDisplayProgressBar(): Boolean = false
    }

    // StateEvent which is good to warn the user using toast, dialog, snackbar etc.
    class CreateStateMessageEvent(
        val stateMessage: StateMessage
    ) : MainStateEvent(){
        override fun errorInfo(): String = "Error creating a new state message"

        override fun eventName(): String = "CreateStateMessageEvent"

        override fun shouldDisplayProgressBar(): Boolean = false

    }

}