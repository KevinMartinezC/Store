package com.example.store

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.store.databinding.ItemStoreBinding

import com.example.store.entities.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.store.repository.StoreRepository

@Suppress("MemberVisibilityCanBePrivate")
class StoreAdapter(private val list: List<Store>, private val context: Context) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>()  {
    class StoreViewHolder(val binding: ItemStoreBinding):
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }


    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        with(holder.binding) {
            txtCant.text = "x" + list[position].amount.toString()
            txtName.text = list[position].name
            txtPrice.text = "$"+ list[position].price.toString()

            imgDelete.setOnClickListener{
                val repository =  StoreRepository.getRepository(context)
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteOneItem(list[position].id)
                }
            }


        }
    }



    override fun getItemCount(): Int = list.size

}