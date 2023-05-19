package org.d3if3111.assessmentmobpro.model

import org.d3if3111.assessmentmobpro.db.VolumeEntity

fun VolumeEntity.hitungVolume(): HasilVolume {
    val volume = nilaiSatu * nilaiDua * nilaiTiga
    return HasilVolume(volume)
}
