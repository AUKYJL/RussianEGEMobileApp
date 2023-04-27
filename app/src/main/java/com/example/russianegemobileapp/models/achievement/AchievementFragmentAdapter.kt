package com.example.russianegemobileapp.models.achievement

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.AchievementItemBinding
import com.example.russianegemobileapp.views.MainActivity


class AchievementFragmentAdapter():RecyclerView.Adapter<AchievementFragmentAdapter.AchieveFragmentHolder>() {
    val achieveFragmentList = ArrayList<AchievementFragment>()

    class AchieveFragmentHolder(item: View,val context: Context):RecyclerView.ViewHolder(item) {
        val binding = AchievementItemBinding.bind(item)
        fun bind(achievFragment: AchievementFragment) = with(binding){
            title.text = achievFragment.title
            desc.text = achievFragment.desc
            val prog = achievFragment.progress
            textProgressBar.text = "$prog%"
            progressBar.progressDrawable = AppCompatResources.getDrawable(context,getNewProgressBarColor(prog))
            progressBar.progress = prog

        }
        fun getNewProgressBarColor(progress:Int): Int {
            when (progress){
                in 1..25 -> return R.drawable.circle_red
                in 26..50 -> return R.drawable.circle_orange
                in 51..75 -> return R.drawable.circle_yellow
                in 76..99 -> return R.drawable.circle_green
                100-> return R.drawable.circle_lime
                else -> return R.drawable.circle_gray
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchieveFragmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.achievement_item,parent,false)
        return AchieveFragmentHolder(view,parent.context)
    }

    override fun getItemCount(): Int {
        return achieveFragmentList.size
    }

    override fun onBindViewHolder(holder: AchieveFragmentHolder, position: Int) {
        holder.bind(achieveFragmentList[position])
    }
    fun addAchieveFragment(theoryFragment: AchievementFragment){
        achieveFragmentList.add(theoryFragment)
        notifyDataSetChanged()
    }
}