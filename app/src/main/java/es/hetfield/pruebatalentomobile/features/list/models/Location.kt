package es.hetfield.pruebatalentomobile.features.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
) : Parcelable, JSONConvertable {

}