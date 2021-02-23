package es.hetfield.pruebatalentomobile

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import es.hetfield.pruebatalentomobile.features.main.MainActivity
import es.hetfield.pruebatalentomobile.setup.di.appComponent
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logDebug
import es.hetfield.pruebatalentomobile.setup.utils.extensions.showNotification
import org.altbeacon.beacon.MonitorNotifier
import org.altbeacon.beacon.Region
import org.altbeacon.beacon.powersave.BackgroundPowerSaver
import org.altbeacon.beacon.startup.BootstrapNotifier
import org.altbeacon.beacon.startup.RegionBootstrap
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class App : MultiDexApplication() {
    lateinit var region: Region
    var regionBootstrap: RegionBootstrap? = null
    lateinit var backgroundPowerSaver: BackgroundPowerSaver
    var monitoringActivity: MainActivity? = null

    override fun onCreate() {

        FirebaseApp.initializeApp(this)

        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appComponent)
        }

        backgroundPowerSaver = BackgroundPowerSaver(this)
    }
}