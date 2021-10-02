package com.example.store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.databinding.ActivityMainBinding
import com.example.store.entities.Store
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.store.repository.StoreRepository

class MainActivity() : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildList()
        addListeners()


    }
    private fun buildList() {
        val repository = StoreRepository.getRepository(this)
        val layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            repository.Products.collect { stores ->
                binding.rvStore.apply{
                    adapter = StoreAdapter(stores, this@MainActivity)
                    setLayoutManager(layoutManager)
                }
                totalPrice(stores)
            }
        }
    }
    private fun addListeners() {
        binding.fbAdd.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))

        }


    }
    private fun totalPrice(list :List<Store> ) {

        var total =0.0

        for(i in 0 until list.size){
            total += list[i].price.toDouble()
        }
        binding.txtTotal.setText("$" + total.toString())
    }



}

