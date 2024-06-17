package com.ilharper.sym.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilharper.sym.databinding.ItemContactBinding

@SuppressLint("NotifyDataSetChanged")
class ContactAdapter(
    private val context: ContactFragment,
    private val viewModel: ContactViewModel,
) :
    RecyclerView.Adapter<ContactViewHolder>() {
    init {
        viewModel.contacts.observe(context) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener {
        }

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int,
    ) {
        val binding: ItemContactBinding = holder.getBinding()
        val contact = viewModel.contacts.value!![position]
        binding.name.text = contact.name
        // binding.setContactmodel(ContactViewModel.of(contact, c))
    }

    override fun getItemCount(): Int = viewModel.contacts.value?.size ?: 0
}
