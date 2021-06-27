package com.shinyj.template.mvi.ui.main

import android.os.Bundle
import com.shinyj.template.mvi.R
import com.shinyj.template.mvi.ui.common.BaseActivity
import com.shinyj.template.mvi.util.CommonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val TAG: String
        get() = CommonUtils.getTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun displayProgressBar(isDisplayed: Boolean) {
        TODO("Not yet implemented")
    }

}