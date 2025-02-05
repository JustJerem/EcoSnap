package com.jeremieguillot.core.presentation.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun loadBitmapFromPath(path: String): Bitmap? {
    return BitmapFactory.decodeFile(File(path).absolutePath)
}