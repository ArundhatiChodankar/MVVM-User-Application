package com.example.mvvmuserapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmuserapplication.databinding.ItemUserBinding
import com.example.mvvmuserapplication.model.data.model.User
import com.example.mvvmuserapplication.view.interfaces.OnItemClickListener

class UserAdapter(val context: Context, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onItemClickListener: OnItemClickListener) {
            binding.user = user
            Glide.with(binding.root).load(user.avatar).into(binding.userProfileIV)

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position], onItemClickListener)
    }
}