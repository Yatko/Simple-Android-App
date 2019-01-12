/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.widget

import android.graphics.*
import android.support.annotation.ColorInt
import com.squareup.picasso.Transformation

/**
 * Created by Alexey Rogovoy (lexapublic@gmail.com) on 29.06.2018.
 */
class CircleTransformation : Transformation {

    private var strokeWidth = 0f
    private var strokeColor = Color.TRANSPARENT

    constructor(){}

    constructor(@ColorInt strokeColor: Int, strokeWidth: Float) : super() {
        this.strokeColor = strokeColor
        this.strokeWidth = strokeWidth
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.setShader(shader)
        paint.setAntiAlias(true)

        val r = size / 2f

        if (strokeColor != Color.TRANSPARENT) {
            // Prepare the background
            val paintBg = Paint()
            paintBg.setColor(strokeColor)
            paintBg.setAntiAlias(true)
            // Draw the background circle
            canvas.drawCircle(r, r, r, paintBg)
        }
        // Draw the image smaller than the background so a little border will be seen
        canvas.drawCircle(r, r, r - strokeWidth, paint)

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}
