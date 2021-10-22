package com.apps.pcomapp.views

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.apps.pcomapp.R
import com.apps.pcomapp.util.Helper
import com.apps.pcomapp.util.MyPreferences
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rgLang.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbEng) {

            } else {

            }
        })

        if (MyPreferences.getValue(requireContext(), "DarkMode").equals("yes", true)) {
            rbOn.isChecked = true
            rbOff.isChecked = false
        } else {
            rbOn.isChecked = false
            rbOff.isChecked = true
        }

        rgNightMode.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbOn) {
                Helper.setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
                MyPreferences.setValue(requireContext(), "DarkMode", "yes")
            } else {
                Helper.setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                MyPreferences.setValue(requireContext(), "DarkMode", "no")
            }

        })

    }
}