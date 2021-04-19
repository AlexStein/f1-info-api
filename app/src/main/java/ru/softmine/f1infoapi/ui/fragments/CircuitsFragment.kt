package ru.softmine.f1infoapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.databinding.FragmentCircuitsBinding
import ru.softmine.f1infoapi.mvp.presenter.CircuitsPresenter
import ru.softmine.f1infoapi.mvp.view.CircuitsView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import ru.softmine.f1infoapi.ui.adapter.CircuitsAdapter

class CircuitsFragment: MvpAppCompatFragment(), CircuitsView, BackClickListener {
    companion object {
        fun newInstance() = CircuitsFragment()
    }

    private var vb: FragmentCircuitsBinding? = null
    private var adapter: CircuitsAdapter? = null

    private val presenter: CircuitsPresenter by moxyPresenter {
        CircuitsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCircuitsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        adapter = CircuitsAdapter(presenter.circuitsListPresenter)
        adapter?.let {
            App.instance.appComponent.inject(it)
        }

        vb?.recyclerViewCircuits?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewCircuits?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}
