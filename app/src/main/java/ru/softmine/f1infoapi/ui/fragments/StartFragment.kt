package ru.softmine.f1infoapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.databinding.FragmentMainBinding
import ru.softmine.f1infoapi.mvp.presenter.StartPresenter
import ru.softmine.f1infoapi.mvp.view.StartView
import ru.softmine.f1infoapi.ui.App

class StartFragment : MvpAppCompatFragment(), StartView {
    companion object {
        fun newInstance() = StartFragment()
    }

    private val presenter: StartPresenter by moxyPresenter {
        StartPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        vb = FragmentMainBinding.inflate(inflater, container, false)

        init()
        return vb?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.buttonCircuits?.setOnClickListener {
            presenter.onCircuitsClicked()
        }

        vb?.buttonSeasons?.setOnClickListener {
            presenter.onSeasonsClicked()
        }

        vb?.buttonTeams?.setOnClickListener {
            presenter.onTeamsClicked()
        }
    }

}