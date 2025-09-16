package com.assignment.therapist_dictonary.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [TherapistLocal::class], version = 4,exportSchema = false)
@TypeConverters(Converters::class)
abstract class TherapistDb: RoomDatabase(){
    abstract fun therapistDao(): TherapistDao

    companion object{
        @Volatile
        private var INSTANCE: TherapistDb? = null

        fun getDatabase(context: Context): TherapistDb{
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TherapistDb::class.java,
                    "TherapistDb"
                ).fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}
