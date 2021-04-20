package ru.softmine.f1infoapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.f1infoapi.databinding.ItemTeamBinding
import ru.softmine.f1infoapi.mvp.model.images.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.teams.TeamItemView
import javax.inject.Inject

class TeamsAdapter(
    private val presenter: ListPresenter<TeamItemView>
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTeamBinding.inflate(
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
    
    inner class ViewHolder(private val vb: ItemTeamBinding) : RecyclerView.ViewHolder(vb.root),
        TeamItemView {
        override var pos = -1

        override fun setName(text: String) = with(vb) {
            textViewTeamName.text = text
        }

        override fun setLogo(imageUrl: String) = with(vb) {
            imageLoader.load(imageUrl, imageTeamLogo)
        }
    }
}