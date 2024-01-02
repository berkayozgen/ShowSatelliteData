package com.example.showsatellitedata.utils.assets

import android.app.Activity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import com.google.gson.GsonBuilder

suspend inline fun <reified T> Activity.loadJson(fileName: String): T? {
    if (!fileName.contains(".json"))
        throw IllegalArgumentException("Unknown file")

    return withContext(Dispatchers.IO) {
        val file = assets.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }

        val jsonString = stringBuilder.toString()
        Gson().fromJson<T>(jsonString, T::class.java)
    }
}