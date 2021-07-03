package ru.softmine.f1infoapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.f1infoapi.databinding.ItemTeamRankingBinding
import ru.softmine.f1infoapi.mvp.model.images.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.teams.TeamRankingItemView
import javax.inject.Inject

class TeamRankingsAdapter(
    private val presenter: ListPresenter<TeamRankingItemView>
) : RecyclerView.Adapter<TeamRankingsAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTeamRankingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })
    
    inner class ViewHolder(private val vb: ItemTeamRankingBinding) : RecyclerView.ViewHolder(vb.root),
        TeamRankingItemView {
        override var pos = -1

        override fun setPosition(position: Int) = with(vb) {
            textViewPosition.text = position.toString()
        }

        override fun setTeamLogo(imageUrl: String) = with(vb)  {
            imageLoader.load(imageUrl, imageTeamLogo)
        }

        override fun setTeamName(name: String) {
            setName(name)
        }

        override fun setPoints(points: Int) = with(vb) {
            textViewPoints.text = points.toString()
        }

        override fun setName(text: String) = with(vb) {
            textViewTeamName.text = text
        }
    }
}