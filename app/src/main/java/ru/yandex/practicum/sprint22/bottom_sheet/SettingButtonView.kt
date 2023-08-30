package ru.yandex.practicum.sprint22.bottom_sheet

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import ru.yandex.practicum.sprint22.R

internal class SettingButtonView : LinearLayout {

    private lateinit var titleTextView: TextView
    private lateinit var subtitleTextView: TextView

    var onSettingClicked: (() -> Unit) = {}
        set(value) {
            field = value
            setOnClickListener { onSettingClicked() }
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    val title get() = titleTextView.text

    val subtitle get() = subtitleTextView.text

    fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.setting_button_view, this, true)
        orientation = VERTICAL
        gravity = Gravity.CENTER
        background = AppCompatResources.getDrawable(context, R.drawable.rect_rounded_8)
        backgroundTintList =
            context.resources.getColorStateList(R.color.fill_glassy_secondary, null)
        titleTextView = view.findViewById(R.id.title)
    }

    fun setTitle(title: String) {
        titleTextView.setText(title)
    }

    fun setTopDrawable(@DrawableRes drawableRes: Int) {
        titleTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, drawableRes, 0, 0)
    }

}