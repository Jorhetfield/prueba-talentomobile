package es.hetfield.pruebatalentomobile.setup.utils.extensions

fun <T> lazyUnsychronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)