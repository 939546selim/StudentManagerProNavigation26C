package com.ahona.studentmanagerpronavigation26c


import android.app.Application
import com.ahona.studentmanagerpronavigation26c.di.AppContainer

class StudentManagerApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }
}