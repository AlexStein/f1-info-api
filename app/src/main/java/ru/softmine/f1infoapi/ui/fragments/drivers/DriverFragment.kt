package ru.softmine.f1infoapi.ui.fragments.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentDriverBinding
import ru.softmine.f1infoapi.mvp.model.entity.Driver
import ru.softmine.f1infoapi.mvp.model.images.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.drivers.DriverPresenter
import ru.softmine.f1infoapi.mvp.view.drivers.DriverView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import javax.inject.Inject


class DriverFragment: MvpAppCompatFragment(), DriverView, BackClickListener {

    companion object {
        const val DRIVER_ARG = "driver"

        fun newInstance(driver: Driver) = DriverFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DRIVER_ARG, driver)
            }
        }
    }

    @Inject lateinit var imageLoader: ImageLoader<ImageView>

    private val presenter: DriverPresenter by moxyPresenter {
        val driver = arguments?.getParcelable<Driver>(DRIVER_ARG) as Driver
        DriverPresenter(driver).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentDriverBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDriverBinding.inflate(inflater, container, false).also {
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
        vb?.imageViewPhoto?.let {
            imageLoader.load(imageUrl, it)
        }
    }

    override fun setNationality(text: String?) {
        vb?.textViewNationality?.text = text ?: getText(R.string.not_available)
    }

    override fun setBirthdate(text: String?) {
        vb?.textViewBirthdate?.text = text ?: getText(R.string.not_available)
    }
}
