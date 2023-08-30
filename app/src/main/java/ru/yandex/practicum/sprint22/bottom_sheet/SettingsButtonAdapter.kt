package ru.yandex.practicum.sprint22.bottom_sheet

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val MAX_NUMBERS_OF_BUTTON_COLUMNS = 3

internal class SettingsButtonsAdapter(
    var settings: List<Setting>,
    private val buttonHeight: Float,
    private val buttonMargin: Int,
    private val fullDialogWidth: Int,
    private val onClick: (Setting) -> Unit,
) :
    RecyclerView.Adapter<SettingsButtonsAdapter.ViewHolder>() {

    class ViewHolder(val settingButtonView: SettingButtonView) :
        RecyclerView.ViewHolder(settingButtonView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(SettingButtonView(parent.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val buttonWidthWithMargins = getWidthByPosition(position) - 2 * buttonMargin
        val buttonHeightWithMargins = buttonHeight.toInt() - 2 * buttonMargin
        holder.settingButtonView.layoutParams =
            GridLayoutManager.LayoutParams(buttonWidthWithMargins, buttonHeightWithMargins).apply {
                setMargins(buttonMargin, buttonMargin, buttonMargin, buttonMargin)
            }
        holder.settingButtonView.setTitle(settings[position].name)
        holder.settingButtonView.setTopDrawable(settings[position].drawableRes)
    }

    override fun getItemCount(): Int = settings.size

    private fun getWidthByPosition(position: Int): Int = when {
        itemCount <= MAX_NUMBERS_OF_BUTTON_COLUMNS -> fullDialogWidth / itemCount
        itemCount == 4 -> fullDialogWidth / 2
        itemCount == 5 -> if (position < MAX_NUMBERS_OF_BUTTON_COLUMNS) fullDialogWidth / MAX_NUMBERS_OF_BUTTON_COLUMNS else fullDialogWidth / 2
        itemCount == 6 -> fullDialogWidth / MAX_NUMBERS_OF_BUTTON_COLUMNS
        itemCount == 7 -> if (position < MAX_NUMBERS_OF_BUTTON_COLUMNS) fullDialogWidth / MAX_NUMBERS_OF_BUTTON_COLUMNS else fullDialogWidth / 2
        itemCount == 8 -> if (position < 6) fullDialogWidth / MAX_NUMBERS_OF_BUTTON_COLUMNS else fullDialogWidth / 2
        else -> fullDialogWidth / MAX_NUMBERS_OF_BUTTON_COLUMNS
    }
}