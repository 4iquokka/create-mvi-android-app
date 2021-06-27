package com.shinyj.template.mvi.util

object StringUtils {

    fun convertListToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    fun convertStringToList(string: String?): List<String> {
        return string?.split(",")?.map { it.trim() } ?: listOf()
    }

}