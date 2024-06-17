package com.ilharper.sym.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.ImageLoader
import com.ilharper.sym.databinding.FragmentContactBinding
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding
    private lateinit var contactAdapter: ContactAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var symriContactJsonAdapter: JsonAdapter<SymriContact>

    private val vm: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        contactAdapter =
            ContactAdapter(
                this,
                requireContext(),
                vm,
                imageLoader,
                symriContactJsonAdapter,
            )
        binding.listView.adapter = contactAdapter
    }
}
