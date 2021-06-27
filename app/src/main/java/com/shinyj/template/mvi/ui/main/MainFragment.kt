package com.shinyj.template.mvi.ui.main

import android.os.Bundle
import com.shinyj.template.mvi.R
import com.shinyj.template.mvi.ui.common.BaseFragment
import com.shinyj.template.mvi.util.CommonUtils

class MainFragment : BaseFragment(R.layout.fragment_main) {

    override val TAG: String
        get() = CommonUtils.getTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}