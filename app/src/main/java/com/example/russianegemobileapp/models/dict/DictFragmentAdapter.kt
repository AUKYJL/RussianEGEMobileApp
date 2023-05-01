package com.example.russianegemobileapp.models.dict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.DictFragmentItemBinding
import com.example.russianegemobileapp.databinding.TheoryFragmentItemBinding
import com.example.russianegemobileapp.models.db.local.dict.Dict

class DictFragmentAdapter:RecyclerView.Adapter<DictFragmentAdapter.DictFragmentHolder>() {
    var dictFragmentList = ArrayList<DictFragment>()
    class DictFragmentHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = DictFragmentItemBinding.bind(item)
        fun bind(dictFragment: DictFragment) = with(binding){
            title.text = dictFragment.title
            desc.text = dictFragment.desc
        }
    }
    fun setFilteredList(list:ArrayList<DictFragment>){
        dictFragmentList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictFragmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dict_fragment_item,parent,false)
        return DictFragmentHolder(view)
    }

    override fun getItemCount(): Int {
        return dictFragmentList.size
    }

    override fun onBindViewHolder(holder: DictFragmentHolder, position: Int) {
        holder.bind(dictFragmentList[position])
    }
    fun addDictFragment(dictFragment: DictFragment){
        dictFragmentList.add(dictFragment)
        notifyDataSetChanged()
    }
}