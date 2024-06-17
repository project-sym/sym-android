package com.ilharper.sym.contact

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.ilharper.sym.R
import com.ilharper.sym.databinding.ItemContactBinding

@SuppressLint("NotifyDataSetChanged")
class ContactAdapter(
    fragment: ContactFragment,
    private val context: Context,
    private val vm: ContactViewModel,
    private val imageLoader: ImageLoader,
) :
    RecyclerView.Adapter<ContactViewHolder>() {
    init {
        vm.contacts.observe(fragment) {
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
        val contact = vm.contacts.value!![position]

        val name = if (contact.name?.isNotEmpty() == true) contact.name!! else "（未知会话）"
        binding.name.text = name

        val avatarDrawable =
            AvatarGenerator
                .AvatarBuilder(context)
                .setLabel(name)
                .setAvatarSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_size))
                .setTextSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_text))
                .toCircle()
                .build()

        if (contact.avatar?.isNotEmpty() == true) {
            imageLoader.enqueue(
                ImageRequest
                    .Builder(context)
                    .data(contact.avatar)
                    .target(binding.avatar)
                    .crossfade(true)
                    .placeholder(avatarDrawable)
                    .transformations(CircleCropTransformation())
                    .build(),
            )
        } else {
            binding.avatar.setImageDrawable(avatarDrawable)
        }

        binding.platformAvatar.setImageDrawable(
            when (contact.platform) {
                "qq", "onebot", "red", "chronocat" ->
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.ic_platform_qq,
                        null,
                    )

                "discord" ->
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.ic_platform_discord,
                        null,
                    )

                else ->
                    AvatarGenerator
                        .AvatarBuilder(context)
                        .setLabel("?")
                        .setAvatarSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_size))
                        .setTextSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_text))
                        .toCircle()
                        .build()
            },
        )
    }

    override fun getItemCount(): Int = vm.contacts.value?.size ?: 0
}
