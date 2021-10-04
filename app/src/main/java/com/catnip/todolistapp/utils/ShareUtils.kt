package com.catnip.todolistapp.utils

import android.content.Context
import android.content.Intent

object ShareUtils {
    fun shareText(context: Context, text: String?){
        val senderIntent = Intent()
        senderIntent.action = Intent.ACTION_SEND
        senderIntent.putExtra(Intent.EXTRA_TEXT,text)
        senderIntent.type = "text/plain"
        val shareIntent = Intent.createChooser(senderIntent, null)
        context.startActivity(shareIntent)
    }
}