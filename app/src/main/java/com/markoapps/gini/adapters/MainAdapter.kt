package com.markoapps.gini.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.markoapps.gini.R
import com.markoapps.gini.databinding.ItemCoppledBinding
import com.markoapps.gini.databinding.ItemRegularBinding
import com.markoapps.gini.viewmodels.IntItem
import com.markoapps.gini.viewmodels.IntType

class MainAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<IntItem>()

    fun setList(list: List<IntItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].type) {
            IntType.regular -> 1
            IntType.coppled -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            1 -> {
                RegularViewHolder(
                    ItemRegularBinding.inflate(inflater, parent, false))
            }
            2  -> {
                CoppledViewHolder(
                    ItemCoppledBinding.inflate(inflater, parent, false))
            }
            else -> {
                RegularViewHolder(
                    ItemRegularBinding.inflate(inflater, parent, false))
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegularViewHolder -> holder.bind(items[position])
            is CoppledViewHolder -> holder.bind(items[position])
        }
    }

    class RegularViewHolder(val itemBinding: ItemRegularBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: IntItem) {
            itemBinding.apply {
                number.text = item.number.toString()
            }
        }
    }

    class CoppledViewHolder(val itemBinding: ItemCoppledBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: IntItem) {
            itemBinding.apply {
                number.text = item.number.toString()
            }
        }
    }

}

