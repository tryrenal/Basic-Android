package com.renaldysabdo.basicandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.renaldysabdo.basicandroid.R
import com.renaldysabdo.basicandroid.databinding.FragmentHomeBinding
import com.renaldysabdo.basicandroid.fragment.adapter.HomeAdapter
import com.renaldysabdo.basicandroid.fragment.adapter.NameModel
import com.renaldysabdo.basicandroid.model.Data
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeAdapter()
        homeAdapter.setData(getData())

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = homeAdapter
    }

    private fun getData() : List<NameModel>{
        return listOf<NameModel>(
            NameModel("Renaldy", "Malang, Indonesia", 15),
            NameModel("Sabdo", "Jogja, Indonesia", 20),
            NameModel("Redveloper", "Kuala Lumpur, Malaysia", 23),
            NameModel("Stepen", "Denpasar, Indonesia", 17),
            NameModel("Jhonny", "Bali, Indonesia", 16),
            NameModel("Kevin", "Jakarta, Indonesia", 51),
            NameModel("Nurul", "Magetan, Indonesia", 35),
            NameModel("Bimbim", "Malang, Indonesia", 21),
        )
    }
}