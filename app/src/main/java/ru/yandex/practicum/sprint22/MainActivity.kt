package ru.yandex.practicum.sprint22

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.transaction
import ru.yandex.practicum.sprint22.bottom_sheet.BottomSheetActivity
import ru.yandex.practicum.sprint22.permissions.loginUri
import ru.yandex.practicum.sprint22.ui.theme.Sprint22Theme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sprint22Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginSendState = remember {
                        mutableStateOf(false)
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ОРИГИНАЛЬНОЕ приложение",
                            modifier = Modifier
                        )
                        Button(onClick = { openBottomSheetScreen() }) {
                            Text(text = "открыть экран с ботомшитом")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            shareLogin()
                            loginSendState.value = true
                        }) {
                            Text(
                                text = "Залогиниться",
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        if (loginSendState.value) {
                            Text(text = "Залогинились и готовы отдавать логин. Сохранили как mperminov")
                        }
                    }
                }
            }
        }
    }

    private fun openBottomSheetScreen() {
        startActivity(Intent(this, BottomSheetActivity::class.java))
    }

    private fun shareLogin() {
        val newValues = ContentValues().apply {
            put("login", "mperminov")
        }
        contentResolver.insert(
            loginUri,
            newValues
        )
    }
}
