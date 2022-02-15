package com.faizil.sehatplus.splashes.vPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.FragmentPage2GSBinding


class Page2GSFragment : Fragment() {
    private lateinit var binding: FragmentPage2GSBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page2_g_s, container, false)
        // Inflate the layout for this fragment
        binding = FragmentPage2GSBinding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem = 2
        return view
    }

}