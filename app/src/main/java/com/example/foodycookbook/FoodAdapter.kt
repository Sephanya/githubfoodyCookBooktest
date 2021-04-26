package com.example.foodycookbook

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.food_cardview_item.view.*

class FoodAdapter(private val data: FoodResponse) : RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(productData: Meal){
            view.food_Name_id.text = productData.strMeal
            Glide.with(view.context).load(productData.strMealThumb).centerCrop().into(view.food_img_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.food_cardview_item, parent, false)
        return  MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data.meals[position])
    }

    override fun getItemCount(): Int {
        return data.meals.size
    }

}