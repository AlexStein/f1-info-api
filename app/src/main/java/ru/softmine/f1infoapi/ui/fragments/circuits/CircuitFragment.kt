package ru.softmine.f1infoapi.ui.fragments.circuits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentCircuitBinding
import ru.softmine.f1infoapi.mvp.model.entity.Circuit
import ru.softmine.f1infoapi.mvp.model.images.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.circuits.CircuitPresenter
import ru.softmine.f1infoapi.mvp.view.circuits.CircuitView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import javax.inject.Inject

class CircuitFragment: MvpAppCompatFragment(), CircuitView, BackClickListener {

    companion object {
        const val CIRCUIT_ARG = "circuit"

        fun newInstance(circuit: Circuit) = CircuitFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CIRCUIT_ARG, circuit)
            }
        }
    }

    @Inject lateinit var imageLoader: ImageLoader<ImageView>

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

    override fun init() {
        App.instance.appComponent.inject(this)
    }

    override fun backPressed() = presenter.backClick()

    override fun setName(text: String) {
        vb?.textViewName?.text = text
    }

    override fun loadImage(imageUrl: String) {
        vb?.imageView?.let {
            imageLoader.load(imageUrl, it)
        }
    }

    override fun setCountry(text: String) {
        vb?.textViewCountry?.text = text
    }

    override fun setCity(text: String) {
        vb?.textViewCity?.text = text
    }

    override fun setLength(len: String) {
        vb?.textViewLength?.text = len
    }

    override fun setOpenDate(year: Int?) {
        vb?.textViewOpened?.text = year?.toString() ?: getText(R.string.not_available)
    }

    override fun setCapacity(capacity: Int) {
        vb?.textViewCapacity?.text = capacity.toString()
    }

    override fun setOwner(text: String?) {
        vb?.textViewOwner?.text = text ?: getText(R.string.not_available)
    }
}
