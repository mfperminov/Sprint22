package ru.yandex.practicum.another

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.another.ui.theme.Sprint22Theme

class AnotherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sprint22Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginState = remember {
                        mutableStateOf("")
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ДРУГОЕ приложение",
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { loginState.value = getLogin() }) {
                            Text(
                                text = "Посмотреть, есть ли сохраненный логин",
                                modifier = Modifier
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        if (loginState.value.isNotEmpty()) {
                            Text(text = "Ура есть! ${loginState.value}")
                        }
                    }
                }
            }
        }
    }

    private fun getLogin(): String {
        contentResolver.query(
            loginUri,
            null,
            null,
            null,
        )?.use {
            val index: Int = it.getColumnIndex("login")
            while (it.moveToNext()) {
                // Gets the value from the column
                return it.getString(index)
            }
        }
        return ""
    }
}

val loginUri: Uri =
    Uri.Builder().scheme("content").authority("ru.yandex.practicum.sprint22").appendPath("login")
        .build()
