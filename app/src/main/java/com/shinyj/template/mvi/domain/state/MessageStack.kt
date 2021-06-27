package com.shinyj.template.mvi.domain.state

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shinyj.template.mvi.util.CommonUtils
import com.shinyj.template.mvi.util.CommonUtils.getPackageName
import kotlinx.parcelize.IgnoredOnParcel
import java.lang.IndexOutOfBoundsException

class MessageStack : ArrayList<StateMessage>() {

    private val TAG = CommonUtils.getTag()

    @IgnoredOnParcel
    private val _stateMessage: MutableLiveData<StateMessage?> = MutableLiveData()

    @IgnoredOnParcel
    val stateMessage: LiveData<StateMessage?>
        get() = _stateMessage

    override fun addAll(elements: Collection<StateMessage>): Boolean {
        for(element in elements){
            add(element)
        }
        return true
    }

    override fun add(element: StateMessage): Boolean {
        if(this.contains(element)){
            return false
        }
        val transaction = super.add(element)
        if(this.size == 1){
            setStateMessage(stateMessage = element)
        }
        return transaction
    }

    override fun removeAt(index: Int): StateMessage {
        try{
            val transaction = super.removeAt(index)
            if(this.size > 0){
                setStateMessage(stateMessage = this[0])
            } else {
                Log.d(TAG, "Stack is empty")
                setStateMessage(null)
            }
            return transaction
        } catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }

        return StateMessage(
            Response(
                message = "Does nothing",
                uiComponentType = UIComponentType.None(),
                messageType = MessageType.None()
            )
        )
    }

    private fun setStateMessage(stateMessage: StateMessage?){
        _stateMessage.value = stateMessage
    }

    companion object{
        val MESSAGE_STACK_BUNDLE_KEY = getPackageName()
    }
}