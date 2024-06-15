package com.ilharper.sym.settings

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference.SummaryProvider
import androidx.preference.PreferenceFragmentCompat
import com.ilharper.sym.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?,
    ) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<EditTextPreference>(getString(R.string.sym_settings_key_server_satori_prefix))!!.summaryProvider =
            SummaryProvider<EditTextPreference> {
                if (it.text.isNullOrEmpty()) getString(R.string.sym_settings_default_server_satori_prefix) else it.text
            }

        findPreference<EditTextPreference>(getString(R.string.sym_settings_key_server_sym_prefix))!!.summaryProvider =
            SummaryProvider<EditTextPreference> {
                if (it.text.isNullOrEmpty()) {
                    getString(
                        R.string.sym_settings_default_server_sym_prefix,
                    )
                } else {
                    it.text
                }
            }
    }
}
