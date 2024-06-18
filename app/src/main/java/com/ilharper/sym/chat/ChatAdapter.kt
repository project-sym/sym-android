package com.ilharper.sym.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.ilharper.sym.R
import com.ilharper.sym.databinding.ItemChatBinding
import com.ilharper.sym.symri.element.Renderer
import com.ilharper.sym.view.RecyclerViewBindingViewHolder
import com.ilharper.symri.entity.ext.resource.SymriContact

@SuppressLint("NotifyDataSetChanged")
class ChatAdapter(
    lifecycleOwner: LifecycleOwner,
    private val context: Context,
    private val vm: ChatViewModel,
    private val imageLoader: ImageLoader,
    private val renderer: Renderer,
    private val contact: SymriContact,
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
        val self = message.user!!.id == contact.logins!![0].selfId
        val name = message.member?.nick ?: message.user?.name ?: ""
        val avatar = message.member?.avatar ?: message.user?.avatar

        // region Set Avatar

        val usedAvatar = if (self) binding.avatarRight else binding.avatarLeft
        val unusedAvatar = if (self) binding.avatarLeft else binding.avatarRight

        val avatarDrawable =
            AvatarGenerator
                .AvatarBuilder(context)
                .setLabel(name)
                .setAvatarSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_size))
                .setTextSize(context.resources.getDimensionPixelOffset(R.dimen.common_avatarimagegenerator_text))
                .toCircle()
                .build()

        imageLoader.enqueue(
            ImageRequest
                .Builder(context)
                .data(avatar)
                .target(usedAvatar)
                .crossfade(true)
                .placeholder(avatarDrawable)
                .fallback(avatarDrawable)
                .transformations(CircleCropTransformation())
                .build(),
        )

        unusedAvatar.setImageResource(android.R.color.transparent)

        usedAvatar.visibility = View.VISIBLE
        unusedAvatar.visibility = View.GONE

        // endregion

        binding.cardContainer.gravity = if (self) Gravity.END else Gravity.START

        binding.name.text = name

        val contentContainer = binding.contentContainer
        contentContainer.removeAllViews()
        renderer.render(context, message.content).forEach(contentContainer::addView)
    }

    override fun getItemCount() = vm.messages.value?.size ?: 0
}
