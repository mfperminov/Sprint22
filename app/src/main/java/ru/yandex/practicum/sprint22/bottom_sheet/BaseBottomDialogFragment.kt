package ru.yandex.practicum.sprint22.bottom_sheet

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.doOnAttach
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * @suppress
 */
open class BaseBottomDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme).apply {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetFrameLayout =
            dialog?.window?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        val containerFrameLayout =
            dialog?.window?.findViewById<FrameLayout>(com.google.android.material.R.id.container)
        bottomSheetFrameLayout?.doOnAttach {
            val insets = it.rootWindowInsets
            containerFrameLayout?.updateLayoutParams<FrameLayout.LayoutParams> {
                setMargins(
                    leftMargin,
                    topMargin,
                    rightMargin,
                    bottomMargin - insets.systemWindowInsetBottom
                )
            }
        }

    }

}