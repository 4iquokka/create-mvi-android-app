package com.shinyj.template.mvi.ui.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.shinyj.template.mvi.domain.state.MessageType
import com.shinyj.template.mvi.domain.state.Response
import com.shinyj.template.mvi.domain.state.StateMessageCallback
import com.shinyj.template.mvi.domain.state.UIComponentType
import com.shinyj.template.mvi.domain.state.UIComponentType.*
import com.shinyj.template.mvi.ui.util.UIController
import com.shinyj.template.mvi.ui.util.displayToast

abstract class BaseActivity : AppCompatActivity(), UIController {

    abstract val TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun hideSoftKeyboard() {
        if(currentFocus != null){
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onResponseRecevied(
        response: Response,
        stateMessageCallback: StateMessageCallback
    ) {
        response.message?.let { message ->
            when (response.uiComponentType) {
                is Toast -> {
                    displayToast(
                        message = message,
                        stateMessageCallback = stateMessageCallback
                    )
                }

                is Dialog -> TODO()

                is SnackBar -> TODO()

                is None -> {
                    when (response.messageType) {
                        is MessageType.Success -> {
                            Log.i(TAG, message)
                        }
                        is MessageType.Error -> {
                            Log.e(TAG, message)
                        }
                        is MessageType.None -> {
                            Log.d(TAG, message)
                        }
                    }
                }
            }
        }
    }

}