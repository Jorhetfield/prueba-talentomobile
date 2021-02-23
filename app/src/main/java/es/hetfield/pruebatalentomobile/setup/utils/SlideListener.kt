package es.hetfield.pruebatalentomobile.setup.utils

import android.view.MotionEvent
import android.view.View
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

/**
 * Slide behaviour like status bar, but from the bottom
 * Must be set as a TouchListener after measures are calculated
 * (not onViewCreated, onCreate or onResume...)
 */
class SlideListener(
    private val slidingView: View,
    private val originalHeight: Int = slidingView.measuredHeight,
    private val animationDuration: Long = 300,
    private val snapLimit: Float = 0.6f,
    private val clickThreshold: Float = 30f
) : View.OnTouchListener {
    var initHeight = 0
    var initPos = 0f
    var deltaPosition = 0f

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                initHeight = slidingView.height
                initPos = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                deltaPosition = initPos - event.rawY
                val deltaHeight = (initHeight - deltaPosition).roundToInt()

                if (deltaHeight < originalHeight) { // Don't go beyond the original height
                    slidingView.layoutParams.height = deltaHeight
                    slidingView.requestLayout() // Refresh layout
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (deltaPosition.absoluteValue < clickThreshold) { // Not enough movement is considered a click
                    deltaPosition = 0f
                    toggle()
                    view.performClick()
                    return true
                }

                if (slidingView.layoutParams.height < originalHeight * snapLimit) {
                    close()
                } else {
                    open()
                }
            }
        }
        view.performClick()
        return true
    }

    fun open() { toggle(false) }
    fun close() { toggle(true) }

    fun toggle() {
        if (slidingView.height == originalHeight) {
            close()
        } else {
            open()
        }
    }

    private fun toggle(close: Boolean) {
        val anim = ResizeAnimation(slidingView)
        anim.duration = animationDuration
        if (close) {
            anim.setParams(slidingView.height, 1)
        } else {
            anim.setParams(slidingView.height, originalHeight)
        }
        slidingView.startAnimation(anim)
    }
}