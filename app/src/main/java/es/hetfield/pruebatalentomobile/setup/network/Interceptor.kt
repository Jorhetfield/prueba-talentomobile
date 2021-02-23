package es.hetfield.pruebatalentomobile.setup.network

import android.util.Log
import es.hetfield.pruebatalentomobile.setup.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class Interceptor(private val prefs: Prefs) : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(Interceptor::class.java.simpleName, "Authorization: Bearer ${prefs.token}")

        val request = chain.request().newBuilder()
        request.addHeader(HttpHeaders.ACCEPT, "application/json")
        prefs.token?.let { authToken ->
            request.addHeader("Authorization", "Bearer $authToken").build()
        }

        return chain.proceed(request.build())
    }
}