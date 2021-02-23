package es.hetfield.pruebatalentomobile.setup

import android.os.Parcelable
import es.hetfield.pruebatalentomobile.setup.utils.extensions.JSONConvertable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActionResponse(
    var message: String?
) : Parcelable, JSONConvertable {}