package ru.softmine.f1infoapi.ui.fragments.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.databinding.FragmentSeasonBinding
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.presenter.seasons.SeasonPresenter
import ru.softmine.f1infoapi.mvp.view.seasons.SeasonView
import ru.softmine.f1infoapi.ui.App

class SeasonFragment : MvpAppCompatFragment(), SeasonView {

    companion object {
        const val SEASON_ARG = "season"

        fun newInstance(season: Season) = SeasonFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SEASON_ARG, season)
            }
        }
    }

    private val presenter: SeasonPresenter by moxyPresenter {
        val season = arguments?.getParcelable<Season>(SEASON_ARG) as Season
        SeasonPresenter(season).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentSeasonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.buttonDrivers?.setOnClickListener {
            presenter.onDriversClicked()
        }

        vb?.buttonTeams?.setOnClickListener {
            presenter.onTeamsClicked()
        }
    }

    override fun setSeasonName(season: String) {
        vb?.textViewSeason?.text = season
    }


}