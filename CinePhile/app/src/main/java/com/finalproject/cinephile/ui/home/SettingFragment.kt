package com.finalproject.cinephile.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finalproject.cinephile.R
import com.finalproject.cinephile.databinding.FragmentSettingBinding
import com.finalproject.cinephile.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding
        get() = _binding!!

    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchRelease.setOnClickListener(this)
        binding.switchDaily.setOnClickListener(this)
        binding.languageSetting.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.switch_release -> if (binding.switchRelease.isChecked) {
               print("testing toggle button final project madd")
            } else {
                print("testing toggle button off final project madd")
            }
            R.id.switch_daily -> if (binding.switchDaily.isChecked) {
                print("testing toggle button final project madd")
            } else {
                print("testing toggle button off final project madd")
            }
            R.id.language_setting -> {
                Intent(Settings.ACTION_LOCALE_SETTINGS).also {
                    startActivity(it)
                }
            }
        }
    }












}