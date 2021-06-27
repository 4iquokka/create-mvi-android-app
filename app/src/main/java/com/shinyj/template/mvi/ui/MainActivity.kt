package com.shinyj.template.mvi.ui

import android.os.Bundle
import com.shinyj.template.mvi.R
import com.shinyj.template.mvi.ui.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}