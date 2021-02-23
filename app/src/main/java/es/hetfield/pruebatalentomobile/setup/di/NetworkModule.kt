package es.hetfield.pruebatalentomobile.setup.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import es.hetfield.pruebatalentomobile.BuildConfig
import es.hetfield.pruebatalentomobile.setup.network.Interceptor
import es.hetfield.pruebatalentomobile.setup.network.Service
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    factory {
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                when {
                    //Filter trash logs
                    message.contains("Content-Type: application/x-www-form-urlencoded") -> print("")
                    message.contains("Content-Length:") -> print("")
                    message.contains("content-type:") -> print("")
                    message.contains("content-length:") -> print("")
                    message.contains("server:") -> print("")
                    message.contains("date:") -> print("")
                    message.contains("access-control-allow-methods:") -> print("")
                    message.contains("access-control-max-age:") -> print("")
                    message.contains("access-control-allow-headers:") -> print("")
                    message.contains("access-control-allow-origin:") -> print("")
                    message.contains("Accept: application/json") -> print("")
                    message.contains("Accept: application/json") -> print("")
                    message.contains("set-cookie:") -> print("")
                    message.contains("Set-Cookie:") -> print("")
                    message.contains("--> END") -> print("")
                    message.contains("<-- END") -> print("")
                    message.isEmpty() -> {print("")}
                    //show valid logs
                    else -> logApi(message)
                }
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    factory { Interceptor(prefs = get()) }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create<Service>() }

}
