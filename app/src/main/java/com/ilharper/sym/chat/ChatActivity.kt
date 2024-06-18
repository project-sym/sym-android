package com.ilharper.sym.chat

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.ilharper.sym.R
import com.ilharper.sym.databinding.ActivityChatBinding
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var symriContactJsonAdapter: JsonAdapter<SymriContact>

    val contact: SymriContact by lazy { symriContactJsonAdapter.fromJson(intent.getStringExtra("data")!!)!! }

    private lateinit var chatAdapter: ChatAdapter

    private val vm: ChatViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<
                ChatViewModelFactory,
            > { factory ->
                factory.create(
                    contact,
                )
            }
        },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.appBar)

        supportActionBar!!.title = contact.name

        val layoutManager = binding.listView.layoutManager as LinearLayoutManager
        layoutManager.apply {
            // reverseLayout = true
            // stackFromEnd = true
        }

        chatAdapter =
            ChatAdapter(
                this,
                this,
                vm,
                imageLoader,
            )
        binding.listView.adapter = chatAdapter
        vm.messages.observe(this) { binding.listView.scrollToPosition(0) }

        binding.appBarContainer.addOnLayoutChangeListener(updateListViewPaddingTop)
        binding.bottomAppBar.addOnLayoutChangeListener(updateListViewPaddingBottom)
    }

    private val updateListViewPaddingTop =
        View.OnLayoutChangeListener {
                v: View?,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
            ->
            binding.listView.updatePadding(top = v!!.height + resources.getDimensionPixelOffset(R.dimen.activity_chat_listview_padding))
        }

    private val updateListViewPaddingBottom =
        View.OnLayoutChangeListener {
                v: View?,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
                _: Int,
            ->
            binding.listView.updatePadding(bottom = v!!.height + resources.getDimensionPixelOffset(R.dimen.activity_chat_listview_padding))
        }
}
