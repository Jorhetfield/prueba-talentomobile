package es.hetfield.pruebatalentomobile.setup.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeAnimation(private val view: View) : Animation() {
    private var startHeight = 0
    private var deltaHeight = 0 // Distance between start and end height

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height = (startHeight + deltaHeight * interpolatedTime).toInt()
        view.requestLayout()
    }

    fun setParams(start: Int, end: Int) {
        startHeight = start
        deltaHeight = end - startHeight
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}