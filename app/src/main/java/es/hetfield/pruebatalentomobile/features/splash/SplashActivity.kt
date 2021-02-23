package es.hetfield.pruebatalentomobile.features.splash

import android.os.Bundle
import es.hetfield.pruebatalentomobile.features.main.MainActivity
import es.hetfield.pruebatalentomobile.setup.BaseActivity
import es.hetfield.pruebatalentomobile.setup.Prefs
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logDebug
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {
    private val prefs: Prefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug("*********** TOKEN FIREBASE ACTUAL: " + prefs.firebaseToken)
        checkToken()
    }

    private fun checkToken() {
        intent = MainActivity.intent(this)
        startActivity(intent)
    }
}

