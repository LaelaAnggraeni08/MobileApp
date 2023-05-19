package org.d3if3111.assessmentmobpro.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3111.assessmentmobpro.db.VolumeDao
import org.d3if3111.assessmentmobpro.db.VolumeEntity
import org.d3if3111.assessmentmobpro.model.HasilVolume
import org.d3if3111.assessmentmobpro.model.hitungVolume

class HitungViewModel(private val db: VolumeDao) : ViewModel() {

    private val hasilVolume = MutableLiveData<HasilVolume?>()

    fun hitungVolume(nilaiSatu: Float, nilaiDua: Float, nilaiTiga: Float) {

        val dataVolume = VolumeEntity(
            nilaiSatu = nilaiSatu,
            nilaiDua = nilaiDua,
            nilaiTiga = nilaiTiga
        )
        hasilVolume.value = dataVolume.hitungVolume()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataVolume = VolumeEntity(
                    nilaiSatu = nilaiSatu,
                    nilaiDua = nilaiDua,
                    nilaiTiga = nilaiTiga
                )
                db.insert(dataVolume)
            }
        }
    }

    fun getHasilVolume(): LiveData<HasilVolume?> = hasilVolume

}