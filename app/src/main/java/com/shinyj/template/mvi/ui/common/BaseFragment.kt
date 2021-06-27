package com.shinyj.template.mvi.ui.common

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shinyj.template.mvi.ui.util.UIController
import java.lang.ClassCastException

abstract class BaseFragment
constructor(
    @LayoutRes val layoutRes: Int
) : Fragment(layoutRes) {

    abstract val TAG : String

    lateinit var uiController: UIController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            uiController = context as UIController
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

}