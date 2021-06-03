package com.teamnoyes.majorparksinseoul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teamnoyes.majorparksinseoul.utils.NetworkState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        NetworkState.initNetworkState(this)
        NetworkState.registerNetworkCallback()
    }

    override fun onStop() {
        super.onStop()
        NetworkState.unRegisterNetworkCallback()
    }
}