package com.ilharper.sym.view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBindingViewHolder<T : ViewDataBinding>(
    val binding: T,
) : RecyclerView.ViewHolder(binding.root) {
    val position: Int
        get() = layoutPosition
}
