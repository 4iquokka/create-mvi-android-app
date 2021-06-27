package com.shinyj.template.mvi.domain.state

import com.shinyj.template.mvi.util.CommonUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext

abstract class DataChannelManager<ViewState> {

    private val TAG = CommonUtils.getTag()

    private var channelScope: CoroutineScope? = null
    private val stateEventManager: StateEventManager = StateEventManager()

    val messageStack = MessageStack()

    val shouldDisplayProgressBar = stateEventManager.shouldDisplayProgressBar

    fun setDataChannel() {
        cancelJobs()
    }

    abstract fun handleNewData(data: ViewState)

    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<ViewState>?>
    ) {
        if (canExecuteNewStateEvent(stateEvent)) {
            addStateEvent(stateEvent)
            jobFunction.onEach { dataState ->
                dataState?.let {
                    withContext(Main) {
                        dataState.data?.let { data ->
                            handleNewData(data)
                        }
                        dataState.stateMessage?.let { message ->
                            handleNewStateMessage(message)
                        }
                        dataState.stateEvent?.let { event ->
                            removeStateEvent(event)
                        }
                    }
                }
            }.launchIn(getChannelScope())
        }
    }

    private fun canExecuteNewStateEvent(stateEvent: StateEvent): Boolean {
        if (isJobAlreadyActive(stateEvent)) {
            return true
        }

        if (!isMessageStackEmpty()) {
            if (messageStack[0].response.uiComponentType == UIComponentType.Toast()) {
                return false
            }
        }

        return true
    }

    fun isMessageStackEmpty(): Boolean = messageStack.isEmpty()

    private fun handleNewStateMessage(stateMessage: StateMessage) = appendStateMessage(stateMessage)

    private fun appendStateMessage(stateMessage: StateMessage) = messageStack.add(stateMessage)

    fun clearStateMessage(index: Int = 0) = messageStack.removeAt(index)

    fun clearAllStateMessages() = messageStack.clear()

    fun clearActiveStateEventCounter() = stateEventManager.clearActiveStateEventCounter()

    fun addStateEvent(stateEvent: StateEvent) = stateEventManager.addStateEvent(stateEvent)

    fun removeStateEvent(stateEvent: StateEvent) = stateEventManager.removeStateEvent(stateEvent)

    fun isJobAlreadyActive(stateEvent: StateEvent): Boolean =
        stateEventManager.isStateEventActive(stateEvent)

    fun getChannelScope(): CoroutineScope = channelScope ?: setNewChannelScope(CoroutineScope(IO))

    private fun setNewChannelScope(coroutineScope: CoroutineScope): CoroutineScope {
        channelScope = coroutineScope
        return channelScope as CoroutineScope
    }

    fun cancelJobs() {
        if (channelScope != null) {
            if (channelScope?.isActive == true) {
                channelScope?.cancel()
            }
            channelScope = null
        }
        clearActiveStateEventCounter()
    }


}