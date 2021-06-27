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

        private val fragmentKtxVersion = "1.2.5"
        val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"

        private val legacyVersion = "1.0.0"
        val legacy = "androidx.legacy:legacy-support-v4:$legacyVersion"

        private val navigationVersion = "2.4.0-alpha03"
        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        val navigationRuntime = "androidx.navigation:navigation-runtime:$navigationVersion"

        private val hiltVersion = "1.0.0-alpha03"
        val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltVersion"

        private val datastoreVersion = "1.0.0-alpha06"
        val datastore = "androidx.datastore:datastore-preferences:$datastoreVersion"
    }

    object Test {
        private val junitExtVersion = "1.1.2"
        val junitExt = "androidx.test.ext:junit:1.1.2"

        private val espressoVersion = "3.3.0"
        val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

        // for Activity Scenario
        val testCoreKtx = "androidx.test:core-ktx:1.2.0"

        // for Fragment Scneario
        val fragmentTest = "androidx.fragment:fragment-testing:1.3.0-alpha06"
    }
}