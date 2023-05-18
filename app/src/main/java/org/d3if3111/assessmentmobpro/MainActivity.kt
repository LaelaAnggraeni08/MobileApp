package org.d3if3111.assessmentmobpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if3111.assessmentmobpro.databinding.ActivityMainBinding
import org.d3if3111.assessmentmobpro.model.HasilVolume
import org.d3if3111.assessmentmobpro.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungVolume() }
        viewModel.getHasilVolume().observe(this, { showResult(it) })

    }

    private fun hitungVolume() {

        val nilaiSatu = binding.nilai1Inp.text.toString()
        if (TextUtils.isEmpty(nilaiSatu)) {
            Toast.makeText(this, R.string.nilai1_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nilaiDua = binding.nilai2Inp.text.toString()
        if (TextUtils.isEmpty(nilaiDua)) {
            Toast.makeText(this, R.string.nilai2_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nilaiTiga = binding.nilai3Inp.text.toString()
        if (TextUtils.isEmpty(nilaiTiga)) {
            Toast.makeText(this, R.string.nilai3_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungVolume(
            nilaiSatu.toFloat(),
            nilaiDua.toFloat(),
            nilaiTiga.toFloat()
        )
    }

    private fun showResult(result: HasilVolume?) {
        if (result == null) return
        binding.hasilTextView.text = getString(R.string.hasil, result.volume)
    }
}