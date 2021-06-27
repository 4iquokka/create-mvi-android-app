package com.shinyj.template.mvi.util

object CommonUtils {

    fun getPackageName() : String = Thread.currentThread().stackTrace[3].className

    fun getClassName() : String {
        val className = getPackageName()
        return className.substring(className.lastIndexOf('.') + 1)
    }

    fun getTag(): String {
        return "[${getClassName()}]"
    }

}