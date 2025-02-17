package com.example.mvvmuserapplication.view.interfaces

import com.example.mvvmuserapplication.model.data.model.User

interface OnItemClickListener {
    fun onDeleteClick(id:String)
    fun onItemClick(user: User)
}