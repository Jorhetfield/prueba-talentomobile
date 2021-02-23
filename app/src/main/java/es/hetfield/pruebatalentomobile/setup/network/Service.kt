package es.hetfield.pruebatalentomobile.setup.network

import es.hetfield.pruebatalentomobile.features.home.models.CenterResponse
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @GET("/egob/catalogo/200304-0-centros-culturales.json")
    suspend fun getCentersInfo(
    ): Response<CenterResponse>

}