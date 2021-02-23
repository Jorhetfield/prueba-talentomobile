package es.hetfield.pruebatalentomobile.setup.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.features.list.models.Center
import es.hetfield.pruebatalentomobile.setup.utils.extensions.inflate


data class BaseModel<T>(val type:Int, val model:T)

enum class AdapterCellType(val typeCell: Int) {
    ADAPTER_TYPE_CENTER(6),
}

class BaseAdapter(
    private val items: MutableList<BaseModel<Any>>,
    private val listener: OnItemListDelegate? = null,
    private val isTeacher: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val holderHashMap: HashMap<Int, RecyclerView.ViewHolder>  = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            AdapterCellType.ADAPTER_TYPE_CENTER.typeCell -> {
                parent.inflate(R.layout.cell_center, false).apply {
                    return CenterHolder(this)
                }
            }
        }

        return BaseHolder(parent) ///"Default"
    }

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CenterHolder -> holder.bind(items[position].model as Center) {
                listener?.onItemClicked(it, position)
            }
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        holderHashMap[holder.bindingAdapterPosition] = holder
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        holderHashMap.remove(holder.bindingAdapterPosition)
        super.onViewAttachedToWindow(holder)

    }

    interface OnItemListDelegate {
        fun onItemClicked(item: Any, position: Int, sender: Int? = null)
    }

    class BaseHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind() {
            Log.d(LOGTAG, "BaseAdapter")
        }
    }

    companion object {
        private val LOGTAG: String = BaseAdapter::class.java.simpleName
    }
}