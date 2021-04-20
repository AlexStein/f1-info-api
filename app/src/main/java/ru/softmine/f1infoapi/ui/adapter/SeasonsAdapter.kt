package ru.softmine.f1infoapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.f1infoapi.databinding.ItemSeasonBinding
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.seasons.SeasonItemView

class SeasonsAdapter(
    private val presenter: ListPresenter<SeasonItemView>
) : RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemSeasonBinding.inflate(
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
    
    inner class ViewHolder(private val vb: ItemSeasonBinding) : RecyclerView.ViewHolder(vb.root),
        SeasonItemView {
        override var pos = -1

        override fun setName(text: String) = with(vb) {
            textViewSeasonName.text = text
        }
    }
}