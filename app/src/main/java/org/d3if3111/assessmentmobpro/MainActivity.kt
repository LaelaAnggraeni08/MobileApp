package org.d3if3111.assessmentmobpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if3111.assessmentmobpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungVolume() }
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
        val volume = nilaiSatu.toFloat() * nilaiDua.toFloat() * nilaiTiga.toFloat()

        binding.hasilTextView.text = getString(R.string.hasil, volume)
    }
}