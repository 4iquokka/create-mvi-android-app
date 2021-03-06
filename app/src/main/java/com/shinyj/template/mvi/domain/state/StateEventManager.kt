package com.shinyj.template.mvi.domain.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StateEventManager {

    private val activeStateEvents: HashMap<String, StateEvent> = HashMap()

    private val _shouldDisplayProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    val shouldDisplayProgressBar: LiveData<Boolean>
        get() = _shouldDisplayProgressBar

    fun getActiveJobNames() : MutableSet<String> {
        return activeStateEvents.keys
    }

    fun clearActiveStateEventCounter(){
        activeStateEvents.clear()
        syncNumActiveStateEvents()
    }

    fun addStateEvent(stateEvent: StateEvent){
        activeStateEvents[stateEvent.eventName()] = stateEvent
        syncNumActiveStateEvents()
    }

    fun removeStateEvent(stateEvent: StateEvent?){
        activeStateEvents.remove(stateEvent?.eventName())
        syncNumActiveStateEvents()
    }

    fun isStateEventActive(stateEvent: StateEvent) : Boolean =
        activeStateEvents.containsKey(stateEvent.eventName())

    private fun syncNumActiveStateEvents(){
        var shouldDisplayProgressbar = false
        for(stateEvent in activeStateEvents.values){
            if(stateEvent.shouldDisplayProgressBar()){
                shouldDisplayProgressbar = true
            }
        }
        _shouldDisplayProgressBar.value = shouldDisplayProgressbar
    }

}