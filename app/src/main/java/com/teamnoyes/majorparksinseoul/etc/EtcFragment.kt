package com.teamnoyes.majorparksinseoul.etc

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.EtcFragmentBinding

class EtcFragment : Fragment(R.layout.etc_fragment) {

    companion object {
        fun newInstance() = EtcFragment()
    }

    private var binding: EtcFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etcFragmentBinding = EtcFragmentBinding.bind(view)
        binding = etcFragmentBinding

        etcFragmentBinding.btnAboutMe.setOnClickListener {
            showDialog(it)
        }

        etcFragmentBinding.btnOSS.setOnClickListener {
            showDialog(it)
        }

        etcFragmentBinding.btnAPIAndLicense.setOnClickListener {
            showDialog(it)
        }
    }

    fun showDialog(view: View) {
        val builder = AlertDialog.Builder(context)
        when (view.id) {
            R.id.btnAboutMe -> {
                builder
                    .setView(R.layout.about_me)
                    .create()
                    .show()
            }
            R.id.btnOSS -> {
                requireContext().startActivity(
                    Intent(context, OssLicensesMenuActivity::class.java)
                )
            }
            R.id.btnAPIAndLicense -> {
                builder
                    .setView(R.layout.api_source)
                    .create()
                    .show()
            }
        }
    }
}