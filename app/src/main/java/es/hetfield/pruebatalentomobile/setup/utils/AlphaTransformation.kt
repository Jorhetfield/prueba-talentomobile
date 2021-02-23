package es.hetfield.pruebatalentomobile.setup.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.squareup.picasso.Transformation

class AlphaTransformation : Transformation {

    /* The shade alpha of black to apply */
    private var mAlpha: Int = 0

    /**
     * Integer Constructor
     *
     * @param alpha the alpha shade to apply
     */
    constructor(alpha: Int) {
        // Clamp the alpha value to 0.255
        mAlpha = Math.max(0, Math.min(255, alpha))
    }

    /**
     * Float Constructor
     *
     * @param percent the alpha percentage from 0.1
     */
    constructor(percent: Float) {
        // Clamp the float value to 0.1
        mAlpha = (Math.max(0f, Math.min(1f, percent)) * 255).toInt()
    }

    /**
     * Transform the source bitmap into a new bitmap. If you create a new bitmap instance, you must
     * call [android.graphics.Bitmap.recycle] on `source`. You may return the original
     * if no transformation is required.
     */
    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint()
        val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)

        // Create canvas and draw the source image
        val canvas = Canvas(output)
        canvas.drawBitmap(source, 0f, 0f, paint)

        // Setup the paint for painting the shade
        paint.color = Color.WHITE
        paint.alpha = mAlpha

        // Paint the shade
        canvas.drawPaint(paint)

        // Recycle and return
        source.recycle()
        return output
    }

    /**
     * Returns a unique key for the transformation, used for caching purposes. If the transformation
     * has parameters (e.g. size, scale factor, etc) then these should be part of the key.
     */
    override fun key(): String {
        return "shade:$mAlpha"
    }
}