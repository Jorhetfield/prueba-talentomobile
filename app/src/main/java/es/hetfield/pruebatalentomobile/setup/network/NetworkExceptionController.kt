package es.hetfield.pruebatalentomobile.setup.network

import android.content.Context
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.utils.extensions.fromJson
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logDebug
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Helpers to handle errors
 */
object NetworkExceptionController {

    fun checkException(context: Context, e: Exception): ResponseResult.Error {
        return when (e) {
            is UnknownHostException -> ResponseResult.Error(context.getString(R.string.network_down), e)
            is SocketTimeoutException -> ResponseResult.Error(context.getString(R.string.network_down_timeout), e)
            else -> defaultError(context, e)
        }
    }

    private fun defaultError(context: Context, e: Exception? = null): ResponseResult.Error {
        logDebug("error por defecto $e")
        return ResponseResult.Error(context.getString(R.string.network_error), e)
    }

    private fun defaultOk(context: Context): ResponseResult.Error {
        return ResponseResult.Error(context.getString(R.string.operation_ok))
    }

    fun <T : Any> checkResponse(context: Context, response: Response<T>): ResponseResult<T> {
        return when {
            response.code() == 204 -> ResponseResult.Error(context.getString(R.string.network_error))
            response.code() in 200..299 ->
                if (response.body() != null) {
                    ResponseResult.Success(response.body() as T)
                } else {
                    response.errorBody()?.string()?.let {
                        val responseResult = it.fromJson<ErrorBean>()
                        ResponseResult.Error(responseResult.message)
                    } ?: defaultOk(context)
                }
            response.code() == 404 -> ResponseResult.Error(context.getString(R.string.network_error))
            response.code() == 401 -> ResponseResult.Error(context.getString(R.string.network_access_error))
            response.code() == 403 -> ResponseResult.Error(context.getString(R.string.network_access_error))
            response.code() == 503 -> ResponseResult.Error(context.getString(R.string.network_down))
            else -> response.errorBody()?.string()?.let {
                val responseResult = it.fromJson<ErrorBean>()
                ResponseResult.Error(responseResult.message)
            } ?: defaultError(context)
        }
    }
}
