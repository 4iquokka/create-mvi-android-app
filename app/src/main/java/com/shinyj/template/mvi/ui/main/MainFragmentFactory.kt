package com.shinyj.template.mvi.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainFragmentFactory
@Inject
constructor(
    // TODO: Inject all the dependencies that are going to be used in the fragments
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            MainFragment::class.java.name -> {
                val fragment = MainFragment()
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}