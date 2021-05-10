package com.renaldysabdo.basicandroid.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.renaldysabdo.basicandroid.R
import com.renaldysabdo.basicandroid.databinding.LayoutItemHomeBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private lateinit var binding: LayoutItemHomeBinding
    private var listItems: ArrayList<NameModel> = ArrayList()

    fun setData(item: List<NameModel>?){
        item?.let {
            listItems.addAll(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_item_home, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(val binding: LayoutItemHomeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: NameModel){
            binding.data = data
            binding.executePendingBindings()
        }
    }
}