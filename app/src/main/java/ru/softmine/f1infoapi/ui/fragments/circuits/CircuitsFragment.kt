package ru.softmine.f1infoapi.ui.fragments.circuits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentListBinding
import ru.softmine.f1infoapi.mvp.presenter.circuits.CircuitsPresenter
import ru.softmine.f1infoapi.mvp.view.circuits.CircuitsView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import ru.softmine.f1infoapi.ui.adapter.CircuitsAdapter

class CircuitsFragment: MvpAppCompatFragment(), CircuitsView, BackClickListener {
    companion object {
        fun newInstance() = CircuitsFragment()
    }

    private var vb: FragmentListBinding? = null
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
    ) = FragmentListBinding.inflate(inflater, container, false).also {
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
        vb?.textViewListTitle?.text = context?.getText(R.string.circuits_title)
        vb?.recyclerViewList?.layoutManager = LinearLayoutManager(context)
        vb?.recyclerViewList?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}
