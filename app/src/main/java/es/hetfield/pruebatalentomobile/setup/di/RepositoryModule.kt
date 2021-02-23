package es.hetfield.pruebatalentomobile.setup.di

import es.hetfield.pruebatalentomobile.setup.network.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory { Repository(service = get(), context = get()) }
}