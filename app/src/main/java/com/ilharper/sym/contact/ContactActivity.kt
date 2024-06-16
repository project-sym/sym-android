package com.ilharper.sym.contact

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ilharper.sym.databinding.ActivityContactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityContactBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.appBar)

        binding.pager.adapter = ContactActivityPagerAdapter(this)
    }
}
