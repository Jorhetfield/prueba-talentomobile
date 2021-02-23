package es.hetfield.pruebatalentomobile.setup.utils.extensions

import es.hetfield.pruebatalentomobile.setup.BaseFragment


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

fun Fragment.findNavControllerFixed(): NavController? {
    return if (NavHostFragment.findNavController(this).currentDestination?.id == NavHostFragment.findNavController(
            this
        ).graph.startDestination
    )
        NavHostFragment.findNavController(this)
    else
        null
}

val BaseFragment.appContext: Context get() = activity?.applicationContext!!