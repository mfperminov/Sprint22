package ru.yandex.practicum.sprint22.permissions

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.core.content.edit

private const val LOGIN_KEY = "LOGIN_KEY"
private const val PREFS_NAME = "prefs"
val loginUri =
    Uri.Builder().scheme("content").authority("ru.yandex.practicum.sprint22").appendPath("login")
        .build()

class LoginProvider : ContentProvider() {

    private lateinit var sharedPreferences: SharedPreferences
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("ru.yandex.practicum.sprint22", "login", 1)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        sharedPreferences.edit {
            putString(LOGIN_KEY, values?.getAsString("login"))
        }
        return null
    }

    override fun onCreate(): Boolean {
        sharedPreferences =
            context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) ?: return false
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return if (uriMatcher.match(uri) == 1) {
            MatrixCursor(arrayOf("login")).apply {
                addRow(arrayOf(sharedPreferences.getString(LOGIN_KEY, null)))
            }
        } else {
            null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}