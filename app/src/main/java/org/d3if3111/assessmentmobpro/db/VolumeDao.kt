package org.d3if3111.assessmentmobpro.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VolumeDao {

    @Insert
    fun insert(volume: VolumeEntity)

    @Query("SELECT * FROM volume ORDER BY id DESC")
    fun getLastVolume(): LiveData<List<VolumeEntity>>

    @Query("DELETE FROM volume")
    fun clearData()
}