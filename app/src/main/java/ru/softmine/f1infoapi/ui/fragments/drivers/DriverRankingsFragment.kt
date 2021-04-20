package ru.softmine.f1infoapi.ui.fragments.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentListBinding
import ru.softmine.f1infoapi.mvp.model.entity.Season
import ru.softmine.f1infoapi.mvp.presenter.drivers.DriverRankingsPresenter
import ru.softmine.f1infoapi.mvp.view.drivers.DriverRankingsView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import ru.softmine.f1infoapi.ui.adapter.DriverRankingsAdapter

class DriverRankingsFragment: MvpAppCompatFragment(), DriverRankingsView, BackClickListener {
    companion object {
        const val SEASON_ARG = "season"

        fun newInstance(season: Season) = DriverRankingsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(SEASON_ARG, season)
            }
        }
    }

    private var vb: FragmentListBinding? = null
    private var adapter: DriverRankingsAdapter? = null

    private val presenter: DriverRankingsPresenter by moxyPresenter {
        val season = arguments?.getParcelable<Season>(SEASON_ARG) as Season
        DriverRankingsPresenter(season).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        adapter = DriverRankingsAdapter(presenter.driversListPresenter)
        adapter?.let {
            App.instance.appComponent.inject(it)
        }

        vb?.textViewListTitle?.text = context?.getText(R.string.driver_rankings)
        vb?.recyclerViewList?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewList?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}
