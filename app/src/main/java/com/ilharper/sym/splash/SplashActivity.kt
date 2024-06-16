package com.ilharper.sym.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ilharper.sym.R
import com.ilharper.sym.contact.ContactActivity
import com.ilharper.sym.contactsvc.ContactService
import com.ilharper.sym.databinding.ActivitySplashBinding
import com.ilharper.sym.msf.Msf
import com.ilharper.sym.settings.SettingsActivity
import com.ilharper.sym.settings.SymSettings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var symSettings: SymSettings

    @Inject
    lateinit var msf: Msf

    @Inject
    lateinit var contactService: ContactService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (tryEnter()) return

        enableEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        tryEnter()
    }

    private fun tryEnter(): Boolean {
        if (!symSettings.checkLoaded()) return false

        startActivity(Intent(this, ContactActivity::class.java))
        finish()

        msf.tryEnsure()
        contactService.refresh()

        return true
    }
}
