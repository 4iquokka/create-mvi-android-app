package com.shinyj.template.mvi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SomeModel(
    val someString : String
) : Parcelable
