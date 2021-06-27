package com.shinyj.template.mvi.buildsrc

object Square {
    // Memory Leak Detection
    object LeakCanary {
        private val leakCanaryVersion = "2.7"
        val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    }
}