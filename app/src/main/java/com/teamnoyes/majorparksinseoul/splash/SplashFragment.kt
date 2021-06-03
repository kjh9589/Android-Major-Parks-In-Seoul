package com.teamnoyes.majorparksinseoul.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.SplashFragmentBinding
import com.teamnoyes.majorparksinseoul.utils.NetworkState

class SplashFragment : Fragment() {
    private lateinit var splashFragmentBinding: SplashFragmentBinding
    private lateinit var splashViewModel: SplashViewModel
    private lateinit var splashViewModelFactory: SplashViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)
        val application = requireNotNull(this.activity).application
        splashViewModelFactory = SplashViewModelFactory(application)
        return splashFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel = ViewModelProvider(this, splashViewModelFactory).get(SplashViewModel::class.java)

        splashViewModel.splashStatus.observe(viewLifecycleOwner, Observer {
            when(it){
                SplashStatus.SUCCESS -> {
                    moveToMain()
                }
                SplashStatus.CLIENT_ERROR -> {
                    Toast.makeText(context, "현재 앱 상 오류가 있습니다.\n빠른 시일 내에 고치겠습니다.", Toast.LENGTH_SHORT).show()
                }
                SplashStatus.SERVER_ERROR -> {
                    Toast.makeText(context, "잠시 후 다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    activity?.finish()
                }
            }
        })

        splashViewModel.getParksData()

    }

    private fun moveToMain(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }

}