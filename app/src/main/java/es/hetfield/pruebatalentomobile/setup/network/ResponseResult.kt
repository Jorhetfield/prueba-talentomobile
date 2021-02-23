package es.hetfield.pruebatalentomobile.setup.network

sealed class ResponseResult<out T : Any> {
    data class Success<out T : Any>(val value: T) : ResponseResult<T>()
    data class Error(val message: String, val cause: Exception? = null) : ResponseResult<Nothing>()
}