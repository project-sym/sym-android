package com.ilharper.sym.channellist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import com.ilharper.sym.contact.ContactAdapter
import com.ilharper.sym.databinding.ActivityChannelListBinding
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import javax.inject.Inject

@AndroidEntryPoint
class ChannelListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChannelListBinding

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var symriContactJsonAdapter: JsonAdapter<SymriContact>

    private lateinit var contactAdapter: ContactAdapter

    val contact: SymriContact by lazy { symriContactJsonAdapter.fromJson(intent.getStringExtra("data")!!)!! }

    private val vm: ChannelListViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<
                ChannelListViewModelFactory,
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

        binding = ActivityChannelListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar)

        supportActionBar!!.title = contact.name
        contactAdapter =
            ContactAdapter(
                this,
                this,
                vm,
                imageLoader,
                symriContactJsonAdapter,
            )
        binding.listView.adapter = contactAdapter
    }
}
