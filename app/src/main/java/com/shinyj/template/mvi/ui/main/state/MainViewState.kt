package com.shinyj.template.mvi.ui.main.state

import android.os.Parcelable
import com.shinyj.template.mvi.domain.state.ViewState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainViewState(
    var someString : String? = null
) : Parcelable, ViewState