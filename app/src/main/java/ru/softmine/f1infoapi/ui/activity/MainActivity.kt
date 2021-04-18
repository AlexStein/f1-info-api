package ru.softmine.f1infoapi.ui.activity

import android.os.Bundle
import moxy.ktx.moxyPresenter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.ActivityMainBinding
import ru.softmine.f1infoapi.mvp.presenter.MainPresenter
import ru.softmine.f1infoapi.mvp.view.MainView
import ru.softmine.f1infoapi.mvp.view.StartView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}