package ru.yandex.practicum.sprint22.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.GridLayoutManager
import ru.yandex.practicum.sprint22.R
import ru.yandex.practicum.sprint22.databinding.SettingsMainDialogFragmentBinding

class Setting(val name: String, @DrawableRes val drawableRes: Int)

/**
 * Отображает главные настройки плеера.
 */

class MainSettingsFragment : BaseBottomDialogFragment() {

    private var binding: SettingsMainDialogFragmentBinding? = null
    private val buttonHeight by lazy { resources.getDimension(R.dimen.setting_button_height) }
    private lateinit var settings: List<Setting>
    private val buttonMargin by lazy { resources.getDimension(R.dimen.setting_button_margin) }
    private val defaultHorizontalPadding by lazy { resources.getDimension(R.dimen.setting_default_padding_horizontal) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = listOf(
            Setting("Качество", R.drawable.baseline_1k_24),
            Setting("Звук", R.drawable.baseline_app_settings_alt_24),
            Setting("Скорость", R.drawable.baseline_bathtub_24),
            Setting("Ошибка", R.drawable.baseline_add_alert_24),
            Setting("Субтитры", R.drawable.baseline_arrow_forward_24),
            Setting("Зум", R.drawable.baseline_anchor_24),
        ).take(5)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        SettingsMainDialogFragmentBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonsContainer(settings, view)
    }

    private fun setupButtonsContainer(
        settings: List<Setting>,
        view: View
    ) {
        val settingButtonsContainerHeight: Int = (settings.size.ceilDivide(MAX_NUMBERS_OF_BUTTON_COLUMNS) * buttonHeight).toInt()
        binding?.settingsMainButtonsContainer?.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, settingButtonsContainerHeight)
        val (spanSize, spanSizeLookupForItemCount) = SettingsSpanSizeLookupFactory.getForSettingsCount(settings.size)
        binding?.settingsMainButtonsContainer?.layoutManager =
            GridLayoutManager(requireContext(), spanSize, GridLayoutManager.VERTICAL, false).apply {
                spanSizeLookup = spanSizeLookupForItemCount
            }
        view.doOnLayout {
            binding?.settingsMainButtonsContainer?.adapter =
                SettingsButtonsAdapter(
                    settings,
                    buttonHeight,
                    buttonMargin.toInt(),
                    it.width - 2 * defaultHorizontalPadding.toInt()
                ) { setting: Setting ->

                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        val TAG: String = MainSettingsFragment::class.java.simpleName
        private const val SETTINGS = "SETTINGS"

    }

}

/** @return result + 1 if remainder is not null, result otherwise */
fun Int.ceilDivide(divider: Int) = this / divider + this.normalizedRemainder(divider)

fun Int.normalizedRemainder(divider: Int) = (this % divider != 0).toInt()

fun Boolean.toInt() = if (this) 1 else 0