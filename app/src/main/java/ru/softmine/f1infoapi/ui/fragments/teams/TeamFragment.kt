package ru.softmine.f1infoapi.ui.fragments.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.softmine.f1infoapi.R
import ru.softmine.f1infoapi.databinding.FragmentTeamBinding
import ru.softmine.f1infoapi.mvp.model.entity.Team
import ru.softmine.f1infoapi.mvp.model.images.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.teams.TeamPresenter
import ru.softmine.f1infoapi.mvp.view.teams.TeamView
import ru.softmine.f1infoapi.ui.App
import ru.softmine.f1infoapi.ui.BackClickListener
import javax.inject.Inject

class TeamFragment: MvpAppCompatFragment(), TeamView, BackClickListener {

    companion object {
        const val TEAM_ARG = "team"

        fun newInstance(team: Team) = TeamFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TEAM_ARG, team)
            }
        }
    }

    @Inject lateinit var imageLoader: ImageLoader<ImageView>

    private val presenter: TeamPresenter by moxyPresenter {
        val team = arguments?.getParcelable<Team>(TEAM_ARG) as Team
        TeamPresenter(team).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentTeamBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTeamBinding.inflate(inflater, container, false).also {
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

    override fun setLogo(imageUrl: String) {
        vb?.imageViewTeamLogo?.let {
            imageLoader.load(imageUrl, it)
        }
    }

    override fun setDirector(text: String?) {
        vb?.textViewTeamDirector?.text = text ?: getText(R.string.not_available)
    }

    override fun setPresident(text: String?) {
        vb?.textViewTeamPresident?.text = text ?: getText(R.string.not_available)
    }

    override fun setTechnicalManager(text: String?) {
        vb?.textViewTeamManager?.text = text ?: getText(R.string.not_available)
    }

    override fun setEngine(text: String?) {
        vb?.textViewTeamEngine?.text = text ?: getText(R.string.not_available)
    }

    override fun setTyres(text: String?) {
        vb?.textViewTeamTyres?.text = text ?: getText(R.string.not_available)
    }
}
