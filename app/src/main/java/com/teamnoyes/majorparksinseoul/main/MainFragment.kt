package com.teamnoyes.majorparksinseoul.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val tabTexts = arrayOf("지역별 공원", "즐겨찾기")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        initToolbar()

        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initToolbar(){
        mainBinding.toolbarMain.inflateMenu(R.menu.main_info_item)
        mainBinding.toolbarMain.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.main_info -> {
                    infoAlertDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun initViewPager(){
        mainAdapter = MainAdapter(this)
        mainBinding.pagerMain.adapter = mainAdapter
        TabLayoutMediator(mainBinding.tabsMain, mainBinding.pagerMain){tab, position ->
            tab.text = tabTexts[position]
        }.attach()
    }

    private fun infoAlertDialog(){
        val builder = AlertDialog.Builder(context)

        builder.setItems(R.array.info) { dialogInterface, pos ->
            when(pos){
                0 -> {
                    showAboutMe()
                }
                1 -> {
                    context?.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                }
                2 -> {
                    showAPISource()
                }
            }
        }

        val mainAlertDialog = builder.create()
        mainAlertDialog.show()
    }

    private fun showAboutMe(){
        val builder = AlertDialog.Builder(context)

        builder.setView(R.layout.about_me)

        val mainAlertDialog = builder.create()
        mainAlertDialog.show()
    }

    private fun showAPISource(){
        val builder = AlertDialog.Builder(context)

        builder.setView(R.layout.api_source)

        val mainAlertDialog = builder.create()
        mainAlertDialog.show()
    }
}