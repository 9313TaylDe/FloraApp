package com.example.floraapp

import android.app.Application
import com.example.floraapp.di.AppContainer


class FloraApplication : Application() {
    val container: AppContainer by lazy {
        AppContainer(this)
    }
}