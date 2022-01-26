package com.example.peopleinroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.peopleinroom.model.User

@Database(entities = arrayOf(User::class), exportSchema = false, version = 1)
abstract class UserDatabase : RoomDatabase() {

    // Функция, возвращающая наш Data Access Obkect
    abstract fun userDao(): UserDao

    companion object {

        //Создаем синглтон UserDatabase, видимый для всех потоков
        @Volatile
        private var INSTANCE: UserDatabase? = null

        //
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE

            // If our instance already exists we return this instance
            if (tempInstance != null) {
                return tempInstance
            }
            // Else if it doesn't exist (if tempInstance is null) we create it:
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}