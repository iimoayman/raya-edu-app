package com.example.raya_edu

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.raya_edu.view.Sign_in
import com.example.raya_edu.viewmodel.SplashScreenViewModel


class MainActivity : AppCompatActivity() {
    private val view:SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply{
            setKeepOnScreenCondition{
                view.isLoading.value
            }
        }

        setContentView(R.layout.fragment_container)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Sign_in())
                .commit()
            supportFragmentManager.executePendingTransactions()
        }
        }
    }

