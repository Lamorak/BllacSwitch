package cz.lamorak.bllacswitch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.times

class SwitchThumb : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        strokeWidth = dip(2).toFloat()
        style = Paint.Style.STROKE
    }

    private var fullRect = RectF()
    private var currentRect = RectF()

    var progress = 0f
            set(value) {
                field = value
                updateRect()
            }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fullRect.set(paddingLeft.toFloat(), paddingTop.toFloat(), w.toFloat() - paddingRight, h.toFloat() - paddingBottom)
        updateRect()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawOval(currentRect, paint)
    }

    private fun updateRect() {
        val dx = progress * fullRect.width() / 2f
        currentRect = RectF(fullRect).apply { inset(dx, 0f) }
        postInvalidate()
    }
}