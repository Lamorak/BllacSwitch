package cz.lamorak.bllacswitch

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_switch.view.*

class BllacSwitch : FrameLayout, Checkable {

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
}