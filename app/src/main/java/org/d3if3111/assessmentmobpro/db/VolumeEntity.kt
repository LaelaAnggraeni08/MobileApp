package org.d3if3111.assessmentmobpro.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volume")
data class VolumeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nilaiSatu: Float,
    var nilaiDua: Float,
    var nilaiTiga: Float
)
