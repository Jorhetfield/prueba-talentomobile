package es.hetfield.pruebatalentomobile.setup

const val MOCK_DELAY = 400L
const val FCM_TOPIC_GENERAL = "general"
const val GMS_API_KEY = "AIzaSyB7wJlezF8E7l5OnxqweqdeOaj_UQ1kLdw"

const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val CEF_SERVER_DATE_FORMAT = "MM/dd/yyyy"
const val SURVEY_DATE_FORMAT = "dd/MM/yyyy"
const val STANDARD_DATE_FORMAT = "EEEE, d 'de' MMMM"

//App constants
const val RATE_0 = "0"
const val RATE_1 = "1"
const val RATE_2 = "2"
const val RATE_3 = "3"
const val RATE_4 = "4"
const val RATE_5 = "5"

//Socket events
const val SOCKET_EVENT_LOCATION = "post_location"
const val SOCKET_EVENT_LOGIN = "add_user"
const val SOCKET_EVENT_MESSAGE = "new_message"

enum class ServiceState(val state: String) {
    NOT_ASSIGNED("1"),
    ASSIGNED("2"),
    ON_THE_WAY("3"),
    FINISHED("4"),
    CANCELED("5")
}
