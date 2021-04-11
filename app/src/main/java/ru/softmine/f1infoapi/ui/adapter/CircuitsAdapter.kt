package ru.softmine.f1infoapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.softmine.f1infoapi.databinding.ItemCircuitBinding
import ru.softmine.f1infoapi.mvp.model.image.ImageLoader
import ru.softmine.f1infoapi.mvp.presenter.interfaces.ListPresenter
import ru.softmine.f1infoapi.mvp.view.common.CircuitItemView
import javax.inject.Inject

class CircuitsAdapter(private val presenter: ListPresenter<CircuitItemView>
) : RecyclerView.Adapter<CircuitsAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: ImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCircuitBinding.inflate(
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


    inner class ViewHolder(private val vb: ItemCircuitBinding) : RecyclerView.ViewHolder(vb.root),
        CircuitItemView {
        override var pos = -1

        override fun setNumber(id: Int) = with(vb) {
            textViewNumber.text = "$id."
        }

        override fun setName(text: String) = with(vb) {
            textViewCircuit.text = text
        }

        override fun loadImage(imageUrl: String) = with(vb) {
            imageLoader.load(imageUrl, imageCircuitMap)
        }
    }
}