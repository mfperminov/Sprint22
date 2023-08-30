package ru.yandex.practicum.sprint22.bottom_sheet

import androidx.recyclerview.widget.GridLayoutManager

internal object SettingsSpanSizeLookupFactory {

    fun getForSettingsCount(itemCount: Int): Pair<Int, GridLayoutManager.SpanSizeLookup> {
        val spanCount = when {
            itemCount <= 3 -> itemCount
            itemCount == 4 -> 2
            itemCount == 5 -> 2 * 3
            itemCount == 6 -> 3
            itemCount in 7..8 -> 2 * 3
            else -> 3
        }
        val spanSizeByPosition: (Int) -> (Int) = when {
            itemCount <= 4 || itemCount == 6 -> { _ -> 1 }
            itemCount == 5 || itemCount == 7 -> { position -> if (position < 3) 2 else 3 }
            itemCount == 8 -> { position -> if (position < 6) 2 else 3 }
            else -> { _ -> 1 }
        }
        return spanCount to object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = spanSizeByPosition(position)
        }
    }

}