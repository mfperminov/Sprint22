package ru.yandex.practicum.sprint22.bottom_sheet

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import ru.yandex.practicum.sprint22.R
import ru.yandex.practicum.sprint22.databinding.ActivityBottomSheetBinding
import ru.yandex.practicum.sprint22.drawable.DiagonalLineDrawable

class BottomSheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
//            MainSettingsFragment().show(supportFragmentManager, "MainSettingsFragment")
        }
        binding.diagonalHolder.background = DiagonalLineDrawable()
    }
}