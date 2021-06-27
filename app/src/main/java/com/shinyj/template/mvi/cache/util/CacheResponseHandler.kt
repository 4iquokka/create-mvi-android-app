package com.shinyj.template.mvi.cache.util

import com.shinyj.kkp.books.cache.util.CacheResult
import com.shinyj.template.mvi.cache.util.CacheConstants.ERROR_CACHE_DATA_NULL
import com.shinyj.template.mvi.domain.state.*

abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>,
    private val stateEvent: StateEvent?
) {

    suspend fun getResult() : DataState<ViewState> {
        return when (response){
            is CacheResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()} Reason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Toast(),
                        messageType = MessageType.Error()
                    ),
                    stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                if(response.value == null) {
                    DataState.error(
                        response = Response(
                            message = "${stateEvent?.errorInfo()} Reason: $ERROR_CACHE_DATA_NULL",
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

    abstract suspend fun handleOnSuccess(resultObj : Data) : DataState<ViewState>
}