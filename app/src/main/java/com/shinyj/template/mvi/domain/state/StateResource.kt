package com.shinyj.template.mvi.domain.state

data class StateMessage(val response: Response)

data class Response(
    val message: String?,
    val uiComponentType: UIComponentType,
    val messageType: MessageType
)

sealed class UIComponentType {

    class Toast : UIComponentType()

    class Dialog : UIComponentType()

    class SnackBar : UIComponentType()

    class None : UIComponentType()
}

sealed class MessageType{

    class Success : MessageType()

    class Error : MessageType()

    class None : MessageType()

}

interface StateMessageCallback{
    fun removeMessageFromStack()
}
