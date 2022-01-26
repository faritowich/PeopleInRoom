package com.example.peopleinroom.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.peopleinroom.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllData()

    @Delete
    suspend fun deleteUser(user: User)
}