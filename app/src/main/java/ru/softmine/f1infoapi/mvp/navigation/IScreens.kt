package ru.softmine.f1infoapi.mvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.softmine.f1infoapi.mvp.model.entity.Circuit


interface IScreens {
    fun start(): Screen
    fun circuits(): Screen
    fun circuit(circuit: Circuit) : Screen
}