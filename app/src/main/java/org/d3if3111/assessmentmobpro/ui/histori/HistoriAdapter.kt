package org.d3if3111.assessmentmobpro.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.databinding.ItemHistoriBinding
import org.d3if3111.assessmentmobpro.db.VolumeEntity
import org.d3if3111.assessmentmobpro.model.hitungVolume
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoriAdapter :
    ListAdapter<VolumeEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<VolumeEntity>() {
                override fun areItemsTheSame(
                    oldData: VolumeEntity, newData: VolumeEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: VolumeEntity, newData: VolumeEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: VolumeEntity) = with(binding) {
            val hasilVolume = item.hitungVolume()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            volumeTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilVolume.volume)

            dataTextView.text = root.context.getString(R.string.data_x,
                item.nilaiSatu, item.nilaiDua, item.nilaiTiga)
        }

    }
}