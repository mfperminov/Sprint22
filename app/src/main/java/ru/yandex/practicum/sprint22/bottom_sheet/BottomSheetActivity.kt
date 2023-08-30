package ru.yandex.practicum.sprint22.bottom_sheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.yandex.practicum.sprint22.databinding.ActivityBottomSheetBinding
import ru.yandex.practicum.sprint22.drawable.DiagonalLineDrawable

class BottomSheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            MainSettingsFragment().show(supportFragmentManager, "MainSettingsFragment")
        }
        binding.diagonalHolder.background = DiagonalLineDrawable()
    }
}