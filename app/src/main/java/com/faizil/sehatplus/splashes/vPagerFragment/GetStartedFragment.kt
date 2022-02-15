package com.faizil.sehatplus.splashes.vPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.FragmentGetStartedBinding
import com.faizil.sehatplus.splashes.ViewPagerAdapter


class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentGetStartedBinding.bind(view)
        val fragmentList = arrayListOf(
            Page1GSFragment(),
            Page2GSFragment(),
            Page3GSFragment()
        )
        val adapter =  ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        return view
    }
}