package es.hetfield.pruebatalentomobile.setup.utils.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.os.PowerManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.network.ResponseResult
import retrofit2.Response

fun Context.getJsonFromResource(res: Int): String {
    return resources.openRawResource(res)
        .bufferedReader().use { it.readText() }
}

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)

inline fun <reified T : Any> Context.getMockListResponse(raw: Int): Response<List<T>> {
    val json = this.getJsonFromResource(raw)
    val response: List<T> = Gson().fromJson(json, Array<T>::class.java).toList()
    return Response.success(response)
}

inline fun <reified T : Any> Context.getMockResponse(raw: Int): Response<T> {
    val json = this.getJsonFromResource(raw)
    val response: T = json.fromJson()
    return Response.success(response)
}

inline fun <reified T : Any> Context.getMockResult(raw: Int): T {
    val json = this.getJsonFromResource(raw)
    return json.fromJson()
}

inline fun <reified T : Any> Context.getMockResponseResult(raw: Int): ResponseResult.Success<T> {
    return ResponseResult.Success(this.getMockResult(raw))
}

inline fun Context.notification(channelId: String, func: NotificationCompat.Builder.() -> Unit): Notification {
    val builder = NotificationCompat.Builder(this, channelId)
    builder.func()
    return builder.build()
}

fun Context.showNotification(notification: RemoteMessage.Notification?, pendingIntent: PendingIntent) {
    showNotificationWith(notification, pendingIntent)
}

fun Context.showNotification(title: String, body: String) {
    showNotificationWith(title = title, body = body)
}

private fun Context.showNotificationWith(notification: RemoteMessage.Notification? = null, pendingIntent: PendingIntent? = null, title: String? = null, body: String? = null) {
    val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val channelId = notification?.channelId ?: getString(R.string.default_notification_channel_id)
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.mipmap.ic_launcher_round))
        .setContentTitle(notification?.title ?: title)
        .setContentText(notification?.body ?: body)
        .setAutoCancel(true)
        .setSound(soundUri)
        .setContentIntent(pendingIntent)

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(0, notificationBuilder.build())
}

val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager

fun Context.color(color: Int) = ContextCompat.getColor(this, color)

fun Context.toastShort(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()