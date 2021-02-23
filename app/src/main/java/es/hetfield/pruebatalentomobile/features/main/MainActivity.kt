package es.hetfield.pruebatalentomobile.features.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(materialToolbar)
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    companion object {
        @JvmStatic
        fun intent(context: Context) = Intent(context, MainActivity::class.java)
    }
}