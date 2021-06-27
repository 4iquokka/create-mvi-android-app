package com.shinyj.template.mvi.buildsrc

object Google {

    object Material {
        private val materialVersion = "1.3.0"
        val material = "com.google.android.material:material:$materialVersion"
    }

    object Hilt {
        private val hiltVersion = "2.35"
        val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        val android = "com.google.dagger:hilt-android:$hiltVersion"
        val compiler = "com.google.dagger:hilt-compiler:$hiltVersion"
        val testCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        val test = "com.google.dagger:hilt-android-testing:$hiltVersion"
    }

}