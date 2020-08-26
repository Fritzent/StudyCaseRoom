package com.example.studycaseroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studycaseroom.database.Item
import com.example.studycaseroom.main.MainActivityPresenter
import kotlinx.android.synthetic.main.suff_item.view.*

class ItemAdapter(private val listItem: List<Item>, private val presenter: MainActivityPresenter ): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suff_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_nama.text = listItem[position].name
        holder.itemView.tv_quantity.text = listItem[position].quantity.toString()

        holder.itemView.iv_delete.setOnClickListener {
            presenter.deleteItem(listItem[position])
        }
        holder.itemView.iv_edit.setOnClickListener {
            presenter.goToEditActivity(listItem[position])
        }
    }
}