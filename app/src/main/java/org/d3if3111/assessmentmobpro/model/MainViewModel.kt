package org.d3if3111.assessmentmobpro.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val hasilVolume = MutableLiveData<HasilVolume?>()

    fun hitungVolume(nilaiSatu: Float, nilaiDua: Float, nilaiTiga: Float) {
        val volume = nilaiSatu * nilaiDua * nilaiTiga
        hasilVolume.value = HasilVolume(volume)
    }

    fun getHasilVolume(): LiveData<HasilVolume?> = hasilVolume

}