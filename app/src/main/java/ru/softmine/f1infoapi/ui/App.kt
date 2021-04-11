package ru.softmine.f1infoapi.ui

import android.app.Application
import ru.softmine.f1infoapi.di.AppComponent
import ru.softmine.f1infoapi.di.DaggerAppComponent
import ru.softmine.f1infoapi.di.module.AppModule
import ru.softmine.f1infoapi.mvp.model.entity.room.db.Database

class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var imageLocation: String
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        imageLocation = this.filesDir.toString()

        Database.create(this)

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build().also { appComponent = it }
    }
}
