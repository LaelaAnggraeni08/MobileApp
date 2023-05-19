package org.d3if3111.assessmentmobpro.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3111.assessmentmobpro.db.VolumeDao

class HistoriViewModel(private val db: VolumeDao) : ViewModel() {

    val data = db.getLastVolume()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}