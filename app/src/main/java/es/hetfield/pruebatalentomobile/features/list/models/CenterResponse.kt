package es.hetfield.pruebatalentomobile.features.home.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import es.hetfield.pruebatalentomobile.features.list.models.Center
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CenterResponse(
    @SerializedName("@graph") val centers: List<Center>
) : Parcelable, JSONConvertable {

}