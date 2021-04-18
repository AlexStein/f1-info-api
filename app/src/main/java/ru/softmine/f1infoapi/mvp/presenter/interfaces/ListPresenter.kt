package ru.softmine.f1infoapi.mvp.presenter.interfaces

interface ListPresenter<V> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}