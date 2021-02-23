package es.hetfield.pruebatalentomobile.setup.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.hetfield.pruebatalentomobile.features.list.models.Center
import kotlinx.android.synthetic.main.cell_center.view.*

class CenterHolder  (v: View) : RecyclerView.ViewHolder(v) {

    private var view: View = v
    private lateinit var item: Center
    private lateinit var listener: (Center) -> Unit

    init {
        v.setOnClickListener {
            listener(this.item)
        }
    }

    fun bind(item: Center?, listener: (Center) -> Unit) {
        item?.let {
            this.item = it
            this.listener = listener

            view.stateLabel.text = item.title
            view.textName.text = item.address.streetAddress
            view.textLocation.text = item.address.locality
        }
    }
}
