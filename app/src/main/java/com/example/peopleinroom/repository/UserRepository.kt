package com.example.peopleinroom.repository

import androidx.lifecycle.LiveData
import com.example.peopleinroom.data.UserDao
import com.example.peopleinroom.model.User

class UserRepository (private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}