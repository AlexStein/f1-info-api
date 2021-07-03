package ru.softmine.f1infoapi.ui.fragments.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentListBinding
import ru.softmine.f1infoapi.mvp.presenter.teams.TeamsPresenter
import ru.softmine.f1infoapi.mvp.view.teams.TeamsView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import ru.softmine.f1infoapi.ui.adapter.TeamsAdapter

class TeamsFragment: MvpAppCompatFragment(), TeamsView, BackClickListener {
    companion object {
        fun newInstance() = TeamsFragment()
    }

    private var vb: FragmentListBinding? = null
    private var adapter: TeamsAdapter? = null

    private val presenter: TeamsPresenter by moxyPresenter {
        TeamsPresenter().apply {
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
        adapter = TeamsAdapter(presenter.teamsListPresenter)
        adapter?.let {
            App.instance.appComponent.inject(it)
        }

        vb?.textViewListTitle?.text = context?.getText(R.string.teams_title)
        vb?.recyclerViewList?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewList?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}
