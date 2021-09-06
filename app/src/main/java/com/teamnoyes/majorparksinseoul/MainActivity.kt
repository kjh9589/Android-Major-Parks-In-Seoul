package com.teamnoyes.majorparksinseoul

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.teamnoyes.majorparksinseoul.databinding.ActivityMainBinding
import com.teamnoyes.majorparksinseoul.util.NetworkLiveData

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val sharedViewModel by lazy {
        ViewModelProvider(
            this, SharedViewModelFactory((application as ParkApplication).parkRepository)
        ).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNavigation()
        setOnStateObserver()

        NetworkLiveData.observe(this, Observer {
            if (it) {
                if (sharedViewModel.state.value != SharedViewModel.SUCCESS) {
                    sharedViewModel.getParksData()
                }
            } else {
                Toast.makeText(this, getString(R.string.plz_check_network), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initBottomNavigation() = with(binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHostFragment.navController

        btmNavigationView.setupWithNavController(navController)
    }

    private fun setOnStateObserver() {
        sharedViewModel.state.observe(this, Observer {
            when(it) {
                SharedViewModel.SUCCESS -> {
                    binding.linearSplash.isGone = true
                    binding.btmNavigationView.isVisible = true
                }
                SharedViewModel.CLIENT_ERROR -> {
                    Toast.makeText(this, "현재 앱 상 오류가 있습니다.\n빠른 시일 내에 고치겠습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                SharedViewModel.SERVER_ERROR -> {
                    Toast.makeText(this, "잠시 후 다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    finish()
                }
                SharedViewModel.UNKNOWN -> {
                    Toast.makeText(this, "현재 앱 상 오류가 있습니다.\n빠른 시일 내에 고치겠습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }
}