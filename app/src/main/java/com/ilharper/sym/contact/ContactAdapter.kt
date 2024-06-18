package com.ilharper.sym.contact

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.ilharper.sym.R
import com.ilharper.sym.channellist.ChannelListActivity
import com.ilharper.sym.chat.ChatActivity
import com.ilharper.sym.databinding.ItemContactBinding
import com.ilharper.sym.view.RecyclerViewBindingViewHolder
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.ilharper.symri.entity.resource.SatoriChannelType
import com.squareup.moshi.JsonAdapter

@SuppressLint("NotifyDataSetChanged")
class ContactAdapter(
    lifecycleOwner: LifecycleOwner,
    private val context: Context,
    private val vm: ContactViewModelBase,
    private val imageLoader: ImageLoader,
    private val symriContactJsonAdapter: JsonAdapter<SymriContact>,
) :
    RecyclerView.Adapter<RecyclerViewBindingViewHolder<ItemContactBinding>>() {
    init {
        vm.contacts.observe(lifecycleOwner) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewBindingViewHolder<ItemContactBinding> {
        val binding =
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )

        val viewHolder =
            RecyclerViewBindingViewHolder(
                binding,
            )

        binding.root.setOnClickListener {
            val contact = vm.contacts.value!![viewHolder.layoutPosition]

            when (contact.type) {
                SatoriChannelType.TEXT ->
                    context.startActivity(
                        Intent(
                            context,
                            ChatActivity::class.java,
                        ).apply {
                            putExtra("data", symriContactJsonAdapter.toJson(contact))
                        },
                    )

                SatoriChannelType.DIRECT ->
                    Toast.makeText(
                        context,
                        "暂不支持私聊频道",
                        Toast.LENGTH_SHORT,
                    ).show()

                SatoriChannelType.CATEGORY ->
                    context.startActivity(
                        Intent(
                            context,
                            ChannelListActivity::class.java,
                        ).apply {
                            putExtra("data", symriContactJsonAdapter.toJson(contact))
                        },
                    )

                SatoriChannelType.VOICE ->
                    Toast.makeText(
                        context,
                        "暂不支持语音频道",
                        Toast.LENGTH_SHORT,
                    ).show()

                else -> throw RuntimeException()
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(
        holder: RecyclerViewBindingViewHolder<ItemContactBinding>,
        position: Int,
    ) {
        val binding = holder.binding
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

        imageLoader.enqueue(
            ImageRequest
                .Builder(context)
                .data(contact.avatar)
                .target(binding.avatar)
                .crossfade(true)
                .placeholder(avatarDrawable)
                .fallback(avatarDrawable)
                .transformations(CircleCropTransformation())
                .build(),
        )

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
