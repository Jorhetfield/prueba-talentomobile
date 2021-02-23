package es.hetfield.pruebatalentomobile.setup.network

import android.content.Context
import es.hetfield.pruebatalentomobile.BuildConfig
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.features.home.models.CenterResponse
import es.hetfield.pruebatalentomobile.setup.MOCK_DELAY
import es.hetfield.pruebatalentomobile.setup.network.NetworkExceptionController.checkException
import es.hetfield.pruebatalentomobile.setup.network.NetworkExceptionController.checkResponse
import es.hetfield.pruebatalentomobile.setup.utils.extensions.getMockResponseResult
import kotlinx.coroutines.delay

class Repository(private val service: Service, private val context: Context) {

    suspend fun getHomeInfo(
        fake: Boolean = BuildConfig.MOCK
    ): ResponseResult<CenterResponse> {
        return if (!fake) {
            try {
                val response = service.getCentersInfo()
                checkResponse(context, response)
            } catch (e: Exception) {
                checkException(context, e)
            }
        } else {
            delay(MOCK_DELAY)
            context.getMockResponseResult(R.raw.home)
        }
    }

}


