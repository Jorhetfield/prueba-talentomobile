package es.hetfield.pruebatalentomobile.features.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    @SerializedName("locality") val locality: String,
    @SerializedName("postal-code") val postalCode: String,
    @SerializedName("street-address") val streetAddress: String
) : Parcelable, JSONConvertable {

}