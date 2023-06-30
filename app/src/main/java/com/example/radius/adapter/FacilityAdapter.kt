package com.example.radius.adapter

import Exclusions
import Options
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.radius.MainActivity
import com.example.radius.R
import com.example.radius.databinding.AdapterFacilityBinding
import com.example.radius.viewmodel.MainViewModel
import com.example.radius.viewmodel.MyViewModelFactory


class FacilityAdapter(context: MainActivity) : RecyclerView.Adapter<MainViewHolder>() {

    var propertyList = mutableListOf<Options>()
    var exclusionHashMap: HashMap<String, String> = HashMap<String, String>()
    var mainContext = context

    fun setProperties(property: List<Options>, exclusion: HashMap<String, String>) {
        this.propertyList = property.toMutableList()
        this.exclusionHashMap = exclusion
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFacilityBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val facility = propertyList[position]

        if (facility.isSelected) {
            holder.binding.cardLayout.setBackgroundColor(Color.parseColor("#262626"));
            holder.binding.name.setTextColor(Color.parseColor("#ffffff"))
        } else {
            holder.binding.cardLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.binding.name.setTextColor(Color.parseColor("#262626"))
        }

        holder.binding.name.text = facility.name
        if (facility.icon == "land") {
            Glide.with(holder.itemView.context).load(R.drawable.land).into(holder.binding.imageview)
        } else if (facility.icon == "apartment") {
            Glide.with(holder.itemView.context).load(R.drawable.apartment)
                .into(holder.binding.imageview)
        } else if (facility.icon == "condo") {
            Glide.with(holder.itemView.context).load(R.drawable.condo)
                .into(holder.binding.imageview)
        } else if (facility.icon == "boat") {
            Glide.with(holder.itemView.context).load(R.drawable.boat).into(holder.binding.imageview)
        } else if (facility.icon == "rooms") {
            Glide.with(holder.itemView.context).load(R.drawable.rooms)
                .into(holder.binding.imageview)
        } else if (facility.icon == "no-room") {
            Glide.with(holder.itemView.context).load(R.drawable.noroom)
                .into(holder.binding.imageview)
        } else if (facility.icon == "swimming") {
            Glide.with(holder.itemView.context).load(R.drawable.swimming)
                .into(holder.binding.imageview)
        } else if (facility.icon == "garden") {
            Glide.with(holder.itemView.context).load(R.drawable.garden)
                .into(holder.binding.imageview)
        } else if (facility.icon == "garage") {
            Glide.with(holder.itemView.context).load(R.drawable.garage)
                .into(holder.binding.imageview)
        }


        holder.binding.card.setOnClickListener {
            if (facility.id == "1" || facility.id == "2" || facility.id == "3" || facility.id == "4") {
                mainContext.resetAdapter()
                for (item in this.propertyList.indices) {
                    this.propertyList[item].isSelected = false
                }
                this.propertyList[position].isSelected = true
                mainContext.selectedProperty = facility.id
                mainContext.selectedOptionId = facility.id
            } else {

                if (mainContext.selectedProperty == "4") {
                    if (facility.id == "6") {
                        mainContext.triggerAlert("${facility.name} not available due previous selection")
                    }
                    else {
                        for (item in this.propertyList.indices) {
                            this.propertyList[item].isSelected = false
                        }
                        mainContext.selectedOptionId = facility.id
                        this.propertyList[position].isSelected = true
                    }

                } else if (mainContext.selectedProperty == "3") {
                    if (facility.id == "12") {
                        mainContext.triggerAlert("${facility.name} not available due previous selection")
                    }
                    else {
                        for (item in this.propertyList.indices) {
                            this.propertyList[item].isSelected = false
                        }
                        mainContext.selectedOptionId = facility.id
                        this.propertyList[position].isSelected = true
                    }
                } else if (mainContext.selectedOptionId == "7") {
                    if (facility.id == "12") {
                        mainContext.triggerAlert("${facility.name} not available due previous selection")
                    }
                    else {
                        for (item in this.propertyList.indices) {
                            this.propertyList[item].isSelected = false
                        }
                        mainContext.selectedOptionId = facility.id
                        this.propertyList[position].isSelected = true
                    }
                } else {
                    for (item in this.propertyList.indices) {
                        this.propertyList[item].isSelected = false
                    }
                    mainContext.selectedOptionId = facility.id
                    this.propertyList[position].isSelected = true
                }

//                var isAvailable = false
//                var pos = 0
//                var firstPos = 0
//                var secondPos = 0
//
//                for (value in mainContext.otherArr.indices) {
//                    if (facility.id == mainContext.otherArr[value]) {
//                        pos = value
//                        isAvailable = true
//                        break
//                    }
//                }
//
//                if (isAvailable) {
//                    for (value in mainContext.propertyArr.indices) {
//                        if (mainContext.propertyArr[value] == mainContext.selectedProperty) {
//                            firstPos = value
//                        }else{
//                            firstPos = pos
//                        }
//                    }
//
//                    for (value in mainContext.propertyArr.indices) {
//                        if (mainContext.propertyArr[value] == mainContext.selectedOptionId) {
//                            secondPos = value
//                        }else{
//                            secondPos = pos
//                        }
//                    }
//                    Log.d("Prop", firstPos.toString())
//                    Log.d("Prop", secondPos.toString())
//
//                    Log.d("Prop",mainContext.otherArr[firstPos] +" "+mainContext.otherArr[pos] +" " +(mainContext.otherArr[firstPos] == mainContext.otherArr[pos]).toString())
//                    Log.d("Prop",(mainContext.otherArr[secondPos] == mainContext.otherArr[pos]).toString())
//
//                   if (firstPos ==0  ){
//                       for (item in this.propertyList.indices) {
//                           this.propertyList[item].isSelected = false
//                       }
//                       mainContext.selectedOptionId = facility.id
//                       this.propertyList[position].isSelected = true
//
//                   } else{
//                       if (mainContext.otherArr[firstPos] == mainContext.otherArr[pos] || mainContext.otherArr[secondPos] == mainContext.otherArr[pos]) {
//
//                           mainContext.triggerAlert("${facility.name} not available due previous selection")
//
//                       } else {
//                           for (item in this.propertyList.indices) {
//                               this.propertyList[item].isSelected = false
//                           }
//                           mainContext.selectedOptionId = facility.id
//                           this.propertyList[position].isSelected = true
//                       }
//                   }
//
//                } else {
//                    for (item in this.propertyList.indices) {
//                        this.propertyList[item].isSelected = false
//                    }
//                    mainContext.selectedOptionId = facility.id
//                    this.propertyList[position].isSelected = true
//                }
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }
}

class MainViewHolder(val binding: AdapterFacilityBinding) : RecyclerView.ViewHolder(binding.root) {

}


//if (exclusionHashMap.containsValue(facility.id)) {
//
//
//    val previousKeys = mainContext.selectedOptionId
//
//
//    mainContext.triggerAlert("${facility.name} not available due previous selection")
//
//
//                    val currentKey = exclusionHashMap.filterValues { it == facility.id }.keys
//
//
//                    var s = currentKey.toString()
//                    s = s.substring(0, s.length).replace("[", "");
//                    s = s.substring(0, s.length).replace("]", "");
//
//                    Log.d(
//                        "Propertyid: ",
//                        "propert id " + propertyKey + " previous id " + previousKeys + " current " + s
//                    )
//
//                    if (s == "") {
//                        for (item in this.propertyList.indices) {
//                            this.propertyList[item].isSelected = false
//                        }
//                        mainContext.selectedOptionId = facility.id
//                        this.propertyList[position].isSelected = true
//                    } else {
//                        if (propertyKey == s || previousKeys == s) {
//                            mainContext.triggerAlert("${facility.name} not available due previous selection")
//                        } else {
//                            for (item in this.propertyList.indices) {
//                                this.propertyList[item].isSelected = false
//                            }
//                            mainContext.selectedOptionId = facility.id
//                            this.propertyList[position].isSelected = true
//                        }
//                    }
//
//
//} else {
//    for (item in this.propertyList.indices) {
//        this.propertyList[item].isSelected = false
//    }
//    mainContext.selectedOptionId = facility.id
//    this.propertyList[position].isSelected = true
//}