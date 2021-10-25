package com.apps.pcomapp.views

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.apps.pcomapp.R
import com.apps.pcomapp.util.Helper
import com.apps.pcomapp.util.MyPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

const val THEME_LIGHT = 0
const val THEME_DARK = 1

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (MyPreferences.getValue(this, "language").equals("hi", true)) {
            setAppLocale1(
                this, this, "hi"
            )
        } else {
            setAppLocale1(
                this, this, "en"
            )
        }
        setContentView(R.layout.activity_main)

        if (MyPreferences.getValue(this, "DarkMode").equals("yes", true)) {
            Helper.setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
        } else {
            Helper.setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcNavHostFramgent) as NavHostFragment
        navController = navHostFragment.findNavController()

        btNav.setupWithNavController(navController)

    }

    fun setAppLocale1(context: Context, activity: Activity, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
