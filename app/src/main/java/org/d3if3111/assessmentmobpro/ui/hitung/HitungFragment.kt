package org.d3if3111.assessmentmobpro.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.databinding.FragmentHitungBinding
import org.d3if3111.assessmentmobpro.db.VolumeDb
import org.d3if3111.assessmentmobpro.model.HasilVolume

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = VolumeDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungVolume() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilVolume().observe(requireActivity(), { showResult(it) })

    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.nilai1Inp.text,
            binding.nilai2Inp.text,
            binding.nilai3Inp.text,
            binding.hasilTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
            return super.onOptionsItemSelected(item)
    }

    private fun hitungVolume() {

        val nilaiSatu = binding.nilai1Inp.text.toString()
        if (TextUtils.isEmpty(nilaiSatu)) {
            Toast.makeText(context, R.string.nilai1_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nilaiDua = binding.nilai2Inp.text.toString()
        if (TextUtils.isEmpty(nilaiDua)) {
            Toast.makeText(context, R.string.nilai2_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val nilaiTiga = binding.nilai3Inp.text.toString()
        if (TextUtils.isEmpty(nilaiTiga)) {
            Toast.makeText(context, R.string.nilai3_invalid, Toast.LENGTH_LONG).show()
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
        binding.shareButton.visibility = View.VISIBLE

    }
}