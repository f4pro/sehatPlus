package com.faizil.sehatplus.splashes.vPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.faizil.sehatplus.R
import com.faizil.sehatplus.databinding.FragmentPage1GSBinding
import com.faizil.sehatplus.databinding.FragmentPage3GSBinding

class Page3GSFragment : Fragment() {
    private lateinit var binding: FragmentPage3GSBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page3_g_s, container, false)
        // Inflate the layout for this fragment
        binding = FragmentPage3GSBinding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem = 3
        return view
    }

}