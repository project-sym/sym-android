package com.ilharper.sym.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.google.android.material.textview.MaterialTextView
import com.ilharper.sym.R
import com.ilharper.sym.databinding.ItemChatBinding
import com.ilharper.sym.view.RecyclerViewBindingViewHolder

@SuppressLint("NotifyDataSetChanged")
class ChatAdapter(
    lifecycleOwner: LifecycleOwner,
    private val context: Context,
    private val vm: ChatViewModel,
    private val imageLoader: ImageLoader,
) :
    RecyclerView.Adapter<RecyclerViewBindingViewHolder<ItemChatBinding>>() {
    init {
        vm.messages.observe(lifecycleOwner) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewBindingViewHolder<ItemChatBinding> {
        val binding =
            ItemChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )

        val viewHolder =
            RecyclerViewBindingViewHolder(
                binding,
            )

        binding.root.setOnClickListener {
            val message = vm.messages.value!![viewHolder.layoutPosition]
        }

        return viewHolder
    }

    override fun onBindViewHolder(
        holder: RecyclerViewBindingViewHolder<ItemChatBinding>,
        position: Int,
    ) {
        val binding = holder.binding
        val message = vm.messages.value!![position]
        val name = message.member?.nick ?: message.user?.name ?: ""
        val avatar = message.member?.avatar ?: message.user?.avatar

        val avatarDrawable =
            AvatarGenerator
                .AvatarBuilder(context)
                .setLabel(name)
                .setAvatarSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_size))
                .setTextSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_text))
                .toCircle()
                .build()

        if (avatar != null) {
            imageLoader.enqueue(
                ImageRequest
                    .Builder(context)
                    .data(avatar)
                    .target(binding.avatar)
                    .crossfade(true)
                    .placeholder(avatarDrawable)
                    .transformations(CircleCropTransformation())
                    .build(),
            )
        } else {
            binding.avatar.setImageDrawable(avatarDrawable)
        }

        val contentContainer = binding.contentContainer
        contentContainer.addView(
            MaterialTextView(context)
                .apply {
                    text = message.content
                },
        )
    }

    override fun getItemCount() = vm.messages.value?.size ?: 0
}
