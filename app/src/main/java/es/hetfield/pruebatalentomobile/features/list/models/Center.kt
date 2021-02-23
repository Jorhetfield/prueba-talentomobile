package es.hetfield.pruebatalentomobile.features.list.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.hetfield.pruebatalentomobile.features.home.models.Address
import es.hetfield.pruebatalentomobile.features.home.models.Location
import es.hetfield.pruebatalentomobile.features.home.models.Organization
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Center(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("relation") val relation: String,
    @SerializedName("address") val address: Address,
    @SerializedName("location") val location: Location,
    @SerializedName("organization") val organization: Organization
) : Parcelable, JSONConvertable {

}