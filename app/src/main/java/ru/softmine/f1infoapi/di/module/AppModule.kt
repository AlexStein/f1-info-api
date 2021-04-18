package ru.softmine.f1infoapi.di.module

import dagger.Module
import dagger.Provides
import ru.softmine.f1infoapi.ui.App

@Module
class AppModule(private val app: App) {
    @Provides
    fun app(): App = app
}