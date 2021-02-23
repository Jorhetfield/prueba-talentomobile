package es.hetfield.pruebatalentomobile.setup

import android.content.Context
import android.content.SharedPreferences
import es.hetfield.pruebatalentomobile.BuildConfig

class Prefs(context: Context) {

    private val FILENAME = "${BuildConfig.APPLICATION_ID}.prefs"
    private val TOKEN = "TOKEN"
    private val FIREBASE_TOKEN = "FIREBASE_TOKEN"
    private val USER_TYPE = "USER_TYPE"
    private val USER_ID = "USER_ID"
    private val USER_NAME = "USER_NAME"

    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.getString(TOKEN, "")
        set(value) = prefs.edit().putString(TOKEN, value).apply()
    var firebaseToken: String?
        get() = prefs.getString(FIREBASE_TOKEN, "")
        set(value) = prefs.edit().putString(FIREBASE_TOKEN, value).apply()
    var userType: String?
        get() = prefs.getString(USER_TYPE, "")
        set(value) = prefs.edit().putString(USER_TYPE, value).apply()
    var userId: Int
        get() = prefs.getInt(USER_ID, 0)
        set(value) = prefs.edit().putInt(USER_ID, value).apply()
    var userName: String?
        get() = prefs.getString(USER_NAME, "")
        set(value) = prefs.edit().putString(USER_NAME, value).apply()

    fun isSessionValid(): Boolean {
        return !this.token.isNullOrEmpty()
    }

    private fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun clearLogin() {
        remove(TOKEN)
        remove(USER_TYPE)
        remove(USER_ID)
        remove(USER_NAME)
    }
}