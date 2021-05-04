package com.renaldysabdo.basicandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.renaldysabdo.basicandroid.R
import com.renaldysabdo.basicandroid.databinding.FragmentHomeBinding
import com.renaldysabdo.basicandroid.model.Data
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Data("redveloper", 23, Date())
        binding.data = data
        binding.tvGreetingFragment.text = "Redveloper"
    }
}