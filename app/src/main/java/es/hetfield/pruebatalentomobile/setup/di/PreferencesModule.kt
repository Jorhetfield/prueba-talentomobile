package es.hetfield.pruebatalentomobile.setup.di

import es.hetfield.pruebatalentomobile.setup.Prefs
import org.koin.dsl.module

val preferencesModule = module {
    factory { Prefs(get()) }
}