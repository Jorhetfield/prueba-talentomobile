package es.hetfield.pruebatalentomobile.features.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Organization(
    @SerializedName("organization-desc") val description: String,
    @SerializedName("accesibility") val accesibility: String,
    @SerializedName("schedule") val schedule: String,
    @SerializedName("services") val services: String,
    @SerializedName("organization-name") val organizationName: String
) : Parcelable, JSONConvertable {

}