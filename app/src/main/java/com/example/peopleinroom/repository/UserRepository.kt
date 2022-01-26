package com.example.peopleinroom.repository

import androidx.lifecycle.LiveData
import com.example.peopleinroom.data.UserDao
import com.example.peopleinroom.model.User

class UserRepository (private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteAllData(){
        userDao.deleteAllData()
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}