package com.ilharper.sym.contact

import androidx.recyclerview.widget.RecyclerView
import com.ilharper.sym.databinding.ItemContactBinding

class ContactViewHolder(b: ItemContactBinding) : RecyclerView.ViewHolder(b.root) {
    private val binding: ItemContactBinding = b

    fun getBinding(): ItemContactBinding {
        return binding
    }
}
