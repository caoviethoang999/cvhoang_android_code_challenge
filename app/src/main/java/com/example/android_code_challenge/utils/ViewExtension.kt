package com.example.android_code_challenge.utils

import android.os.SystemClock
import android.view.View

fun View.clickWithDebounce(debounceTime: Long = 600L, actionIfNotSatisfied: () -> Unit, action: () -> Unit) {

    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                actionIfNotSatisfied()
            } else {
                action()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })

}