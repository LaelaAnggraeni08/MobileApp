package org.d3if3111.assessmentmobpro.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VolumeEntity::class], version = 1, exportSchema = false)
abstract class VolumeDb : RoomDatabase() {
    abstract val dao: VolumeDao
    companion object {
        @Volatile
        private var INSTANCE: VolumeDb? = null
        fun getInstance(context: Context): VolumeDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VolumeDb::class.java,
                        "volume.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
