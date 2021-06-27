package com.shinyj.template.mvi.network.util

import com.shinyj.template.mvi.domain.state.*
import com.shinyj.template.mvi.network.util.NetworkConstants.ERROR_NETWORK
import com.shinyj.template.mvi.network.util.NetworkConstants.ERROR_NETWORK_DATA_NULL

abstract class NetworkResponseHandler<ViewState, Data>(
    private val response: NetworkResult<Data?>,
    private val stateEvent: StateEvent?
) {
    suspend fun getResult() : DataState<ViewState>? {
        return when(response) {
            is NetworkResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message= "${stateEvent?.errorInfo()}, REASON: ${response.errorMessage}}",
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is NetworkResult.NetworkError -> {
                DataState.error(
                    response = Response(
                        message= "${stateEvent?.errorInfo()}, REASON: $ERROR_NETWORK}",
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is NetworkResult.Success -> {
                if(response.value == null) {
                    DataState.error(
                        response = Response(
                            message= "${stateEvent?.errorInfo()}, REASON: $ERROR_NETWORK_DATA_NULL}",
                            uiComponentType = UIComponentType.Toast(),
                            messageType = MessageType.Error()
                        ),
                        stateEvent = stateEvent
                    )
                } else {
                    handleOnSuccess(resultObj = response.value)
                }
            }
        }
    }

    abstract suspend fun handleOnSuccess(resultObj: Data) : DataState<ViewState>?
}