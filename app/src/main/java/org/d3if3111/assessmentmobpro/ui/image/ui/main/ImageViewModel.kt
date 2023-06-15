package org.d3if3111.assessmentmobpro.ui.image.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.ui.image.model.Image

class ImageViewModel  : ViewModel() {

    private val data = MutableLiveData<List<Image>>()

    init {
        data.value = initData()
    }

    // Data ini akan kita ambil dari server di langkah selanjutnya
    private fun initData(): List<Image> {
        return listOf(
            Image("Kubus", "V = s x s x s", R.drawable.kubus),
            Image("Balok", "V = p x l x t", R.drawable.balok),
            Image("Tabung", "V = π x r² x t", R.drawable.tabung),
            Image("Kerucut", "V = 1/3 x π x r² x t", R.drawable.kerucut),
            Image("Bola", "V = 4/3 x π x r³", R.drawable.bola),
            Image("Limas Segitiga", "V = 1/3 x luas alas x t", R.drawable.limassegitiga),
            Image("Limas Segiempat", "V = 1/3 x luas alas x t", R.drawable.limassegiempat),
            Image("Prisma Segitiga", "V = (1/2 x a x t) x tinggi prisma", R.drawable.prismasegitiga),
        )
    }

    fun getData(): LiveData<List<Image>> = data
}
