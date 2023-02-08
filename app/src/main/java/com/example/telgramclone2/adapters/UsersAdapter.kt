package com.example.telgramclone2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telgramclone2.databinding.UsersItemLyBinding

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ItemHolder>() {
    var baseList = emptyList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner  class ItemHolder(val b: UsersItemLyBinding): RecyclerView.ViewHolder(b.root){
        fun bind(itemData: String){
            b.user.text = itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(UsersItemLyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = baseList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.bind(baseList[position])
    }
}