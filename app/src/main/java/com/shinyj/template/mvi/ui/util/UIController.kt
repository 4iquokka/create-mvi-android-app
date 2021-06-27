package com.shinyj.template.mvi.ui.util

import com.shinyj.template.mvi.domain.state.Response
import com.shinyj.template.mvi.domain.state.StateMessageCallback

interface UIController {

    fun displayProgressBar(isDisplayed : Boolean)

    fun hideSoftKeyboard()

    fun onResponseRecevied(
        response : Response,
        stateMessageCallback : StateMessageCallback
    )

}