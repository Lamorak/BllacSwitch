package cz.lamorak.bllacswitch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.widget.Checkable
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.layout_switch.view.*

class BllacSwitch : FrameLayout, Checkable, MotionLayout.TransitionListener {

    private var listener: (Boolean) -> Unit = {}

    private var isChecked = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        View.inflate(context, R.layout.layout_switch, this)
        setOnClickListener {
            toggle()
        }
        track.addTransitionListener(this)
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        setChecked(!isChecked)
    }

    override fun setChecked(check: Boolean) {
        isChecked = check
        listener.invoke(isChecked)
        transition()
    }

    fun setOnCheckedChangeListener(listener: (Boolean) -> Unit) {
        this.listener = listener
    }

    private fun transition() {
        val endState = if (isChecked) R.id.checked else R.id.unchecked
        track.transitionToState(endState)
    }

    override fun onTransitionTrigger(layout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

    override fun onTransitionStarted(layout: MotionLayout?, startId: Int, endId: Int) {}

    override fun onTransitionChange(layout: MotionLayout?, startId: Int, endId: Int, transitionProgress: Float) {
        thumb.progress = transitionProgress
    }

    override fun onTransitionCompleted(layout: MotionLayout?, currentId: Int) {
        if (currentId == R.id.checked) {
            thumb.progress = 1f
            setChecked(true)
        } else {
            thumb.progress = 0f
            setChecked(false)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == ACTION_UP && event.eventTime - event.downTime < 100) {
            performClick()
            return true
        }
        return super.dispatchTouchEvent(event)
    }
}