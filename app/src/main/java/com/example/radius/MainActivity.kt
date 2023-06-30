package com.example.radius

import Property
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.radius.adapter.FacilityAdapter
import com.example.radius.databinding.ActivityMainBinding
import com.example.radius.networkcalls.RetrofitService
import com.example.radius.repo.FacilityRepository
import com.example.radius.viewmodel.MainViewModel
import com.example.radius.viewmodel.MyViewModelFactory
import java.util.Arrays
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = FacilityAdapter(this)
    private val adapterRoom = FacilityAdapter(this)
    private val adapterFac = FacilityAdapter(this)
    val hashMap : HashMap<String, String>
            = HashMap<String, String> ()

    lateinit var binding: ActivityMainBinding
    lateinit var propertyLists: Property
    var selectedProperty = "0"
    var selectedOptionId = "0"
    var propertyArr = ArrayList<String>();
    var otherArr = ArrayList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FacilityRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        binding.recyclerviewRoom.adapter = adapterRoom
        binding.recyclerviewOther.adapter = adapterFac


        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.propertyList.observe(this) {

            val exclusionData = it.exclusions

            for ((index, value) in exclusionData.withIndex()) {
                hashMap.put(exclusionData[index][0].options_id , exclusionData[index][1].options_id)
            }

            for ((index, value) in exclusionData.withIndex()) {
                propertyArr.add(exclusionData[index][0].options_id)
            }
            for ((index, value) in exclusionData.withIndex()) {
                otherArr.add(exclusionData[index][1].options_id)
            }

            System.out.println(Arrays.asList(hashMap));
            adapter.setProperties(it.facilities[0].options,hashMap)
            adapterRoom.setProperties(it.facilities[1].options,hashMap)
            adapterFac.setProperties(it.facilities[2].options,hashMap)
            propertyLists = it as Property
            binding.recyclerview.visibility = View.VISIBLE
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun resetAdapter(){
        //Toast.makeText(this,"adp",Toast.LENGTH_SHORT).show()
        adapterRoom.setProperties(propertyLists.facilities[1].options,hashMap)
        adapterFac.setProperties(propertyLists.facilities[2].options,hashMap)
        for (item in propertyLists.facilities.indices){
            for (items in propertyLists.facilities[item].options.indices){
                propertyLists.facilities[item].options[items].isSelected = false
            }
        }
        adapterRoom.notifyDataSetChanged()
        adapterFac.notifyDataSetChanged()
    }

    fun triggerAlert( msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()

    }
}