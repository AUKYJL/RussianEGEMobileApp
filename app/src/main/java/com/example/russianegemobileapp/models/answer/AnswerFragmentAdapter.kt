package com.example.russianegemobileapp.models.answer

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.russianegemobileapp.App
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.AnswerItemBinding
import java.util.*
import kotlin.collections.ArrayList

class AnswerFragmentAdapter:RecyclerView.Adapter<AnswerFragmentAdapter.AnswerFragmentHolder>() {
    val answerFragmentList = ArrayList<AnswerFragment>()
    class AnswerFragmentHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = AnswerItemBinding.bind(item)
        fun bind(answerFragment: AnswerFragment) = with(binding){
            openTaskText.text = String.format(App.context!!.resources.getString(R.string.open_one_number_answer),answerFragment.openNumberOfAnswer)
            price.text = answerFragment.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerFragmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_item,parent,false)
        return AnswerFragmentHolder(view)
    }

    override fun getItemCount(): Int {
        return answerFragmentList.size
    }

    override fun onBindViewHolder(holder: AnswerFragmentHolder, position: Int) {
        holder.bind(answerFragmentList[position])
    }
    fun addAnswerFragment(answerFragment: AnswerFragment){
        answerFragmentList.add(answerFragment)
        notifyDataSetChanged()
    }
}