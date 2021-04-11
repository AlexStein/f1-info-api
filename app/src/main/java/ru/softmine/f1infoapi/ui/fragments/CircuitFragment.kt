package ru.softmine.f1infoapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.databinding.FragmentCircuitBinding
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.presenter.CircuitPresenter
import ru.softmine.f1infoapi.mvp.view.CircuitView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import java.util.*

class CircuitFragment: MvpAppCompatFragment(), CircuitView, BackClickListener {

    companion object {
        const val CIRCUIT_ARG = "circuit"

        fun newInstance(circuit: Circuit) = CircuitFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CIRCUIT_ARG, circuit)
            }
        }
    }

    private val presenter: CircuitPresenter by moxyPresenter {
        val circuit = arguments?.getParcelable<Circuit>(CIRCUIT_ARG) as Circuit
        CircuitPresenter(circuit).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentCircuitBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCircuitBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backClick()

    override fun setName(text: String) {
        TODO("Not yet implemented")
    }

    override fun loadImage(text: String) {
        TODO("Not yet implemented")
    }

    override fun setLength(len: Int) {
        TODO("Not yet implemented")
    }

    override fun setOpenDate(date: Date) {
        TODO("Not yet implemented")
    }
}
