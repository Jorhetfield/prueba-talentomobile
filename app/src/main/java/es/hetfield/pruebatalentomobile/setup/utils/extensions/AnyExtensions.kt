package es.hetfield.pruebatalentomobile.setup.utils.extensions

import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import java.util.*

val Any.LOGTAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

val Any?.safe get() = Unit

const val FILTER = "DevFilter"

fun Any.logWTF(text: String) {
    val message = " \n------------------------------WTF\n$text\n------------------------------\n"
    Log.wtf("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logWTF(text: String, exception: Exception) {
    Log.wtf("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logInfo(text: String) {
    val message = "------------------------------Info\n$text\n-----------------------------\n"
    Log.i("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logInfo(text: String, exception: Exception) {
    Log.i("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logError(text: String) {
    val message = " \n-----------------------------Error\n$text\n-----------------------------\n"
    Log.e("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logError(text: String, exception: Exception) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logWarn(text: String) {
    val message = " \n----------------------------Warning\n$text\n----------------------------\n"
    Log.w("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logWarn(text: String, exception: Exception) {
    Log.w("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logDebug(text: String) {
    val message = " \n------------------------------Log\n$text\n------------------------------\n"
    Log.d("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logDebug(text: String, exception: Exception) {
    Log.d("$FILTER: ${this::class.java.simpleName}", text, exception)
}

fun Any.logVerbose(text: String) {
    val message = "----------------------------Verbose\n$text\n----------------------------\n"
    Log.v("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logApi(text: String) { ///FIXME: El JSON no se muestra como se espera
    when {
        text.contains("{") -> {
            text.replace("{", " {\n\t ")
        }
        text.contains("}") -> {
            text.replace("}", " \n} ")
        }
        text.contains(",") -> {
            text.replace(",", " ,\n ")
        }
        text.contains("[") -> {
            text.replace("[", " [\n\t ")
        }
        text.contains("]") -> {
            text.replace("]", " \n] ")
        }
        text.contains("&") -> {
            text.replace("&", " &\n ")
        }
    }
    val message =
        "API->\n------------------------------------------------------------------\n$text\n------------------------------------------------------------------"
    Log.v("$FILTER: ${this::class.java.simpleName}", message)
}

fun Any.logVerbose(text: String, exception: Exception) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text, exception)
}

inline fun <T : View> T.afterMeasure(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

inline fun Timer.schedule(
    delay: Long,
    crossinline action: TimerTask.() -> Unit
) {

}