package cz.lamorak.bllacswitch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

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
        strokeWidth = 4f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, width / 3f, paint)
    }
}