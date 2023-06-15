package org.d3if3111.assessmentmobpro.ui.image.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.databinding.ListImageBinding
import org.d3if3111.assessmentmobpro.ui.image.model.Image

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private val data = mutableListOf<Image>()

    fun updateData(newData: List<Image>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) = with(binding) {
            namabangunTextView.text = image.nama
            rumusTextView.text = image.rumus
            imageView.setImageResource(image.imageResId)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, image.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListImageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}