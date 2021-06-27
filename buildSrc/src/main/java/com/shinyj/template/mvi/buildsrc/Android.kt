package com.shinyj.template.mvi.buildsrc

object Android {
    private val gradlePluginVersion = "4.2.1"
    val gradlePlugin = "com.android.tools.build:gradle:$gradlePluginVersion"

    object Jetpack {
        private val coreKtxVersion = "1.3.1"
        val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

        private val appCompatVersion = "1.3.0"
        val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private val constraintVersion = "2.0.1"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintVersion"
    }

    object Test {
        private val junitExtVersion = "1.1.2"
        val junitExt = "androidx.test.ext:junit:1.1.2"

        private val espressoVersion = "3.3.0"
        val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
    }
}