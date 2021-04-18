package ru.softmine.f1infoapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.databinding.FragmentCircuitsBinding
import ru.softmine.f1infoapi.databinding.FragmentSeasonsBinding
import ru.softmine.f1infoapi.mvp.presenter.SeasonsPresenter
import ru.softmine.f1infoapi.mvp.view.SeasonsView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import ru.softmine.f1infoapi.ui.adapter.SeasonsAdapter

class SeasonsFragment: MvpAppCompatFragment(), SeasonsView, BackClickListener {
    companion object {
        fun newInstance() = SeasonsFragment()
    }

    private var vb: FragmentSeasonsBinding? = null
    private var adapter: SeasonsAdapter? = null

    private val presenter: SeasonsPresenter by moxyPresenter {
        SeasonsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSeasonsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        adapter = SeasonsAdapter(presenter.seasonsListPresenter)
        adapter?.let {
            App.instance.appComponent.inject(it)
        }

        vb?.recyclerViewSeasons?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewSeasons?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}
