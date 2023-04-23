package com.example.russianegemobileapp.models.theory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.TheoryFragmentItemBinding

class TheoryFragmentAdapter:RecyclerView.Adapter<TheoryFragmentAdapter.TheoryFragmentHolder>() {
    val theoryFragmentList = ArrayList<TheoryFragment>()
    class TheoryFragmentHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = TheoryFragmentItemBinding.bind(item)
        fun bind(theoryFragment: TheoryFragment) = with(binding){
            title.text = theoryFragment.title
            desc.text = theoryFragment.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheoryFragmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.theory_fragment_item,parent,false)
        return TheoryFragmentHolder(view)
    }

    override fun getItemCount(): Int {
        return theoryFragmentList.size
    }

    override fun onBindViewHolder(holder: TheoryFragmentHolder, position: Int) {
        holder.bind(theoryFragmentList[position])
    }
    fun addTheoryFragment(theoryFragment: TheoryFragment){
        theoryFragmentList.add(theoryFragment)
        notifyDataSetChanged()
    }
}