package com.ilharper.sym.settings

import androidx.preference.PreferenceManager
import com.ilharper.sym.R
import com.ilharper.sym.app.SymApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SymSettings
    @Inject
    constructor(
        private val app: SymApplication,
    ) {
        fun checkLoaded(): Boolean {
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(app)

            url = sharedPreferences.getString(app.getString(R.string.sym_settings_key_server_url), "")!!
            token =
                sharedPreferences.getString(app.getString(R.string.sym_settings_key_server_token), "")!!
            satoriPrefix =
                sharedPreferences.getString(
                    app.getString(R.string.sym_settings_key_server_satori_prefix),
                    app.getString(R.string.sym_settings_default_server_satori_prefix),
                )!!
            symPrefix =
                sharedPreferences.getString(
                    app.getString(R.string.sym_settings_key_server_sym_prefix),
                    app.getString(R.string.sym_settings_default_server_sym_prefix),
                )!!

            return url.isNotEmpty()
        }

        init {
            checkLoaded()
        }

        var url = ""
        var token = ""
        var satoriPrefix = ""
        var symPrefix = ""
    }
