package com.shinyj.template.mvi.buildsrc

object JetBrains {

    object Kotlin {
        private val kotlinVersion = "1.5.10"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        val test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
    }

    object Coroutines {
        private val coroutinesVersion = "1.4.3"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
    }

}