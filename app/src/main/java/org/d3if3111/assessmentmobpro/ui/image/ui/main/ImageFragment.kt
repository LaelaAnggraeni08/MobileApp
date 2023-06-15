package org.d3if3111.assessmentmobpro.ui.image.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if3111.assessmentmobpro.R
import org.d3if3111.assessmentmobpro.databinding.FragmentImageBinding
import org.d3if3111.assessmentmobpro.ui.image.data.SettingDataStore
import org.d3if3111.assessmentmobpro.ui.image.data.dataStore
import org.d3if3111.assessmentmobpro.ui.image.model.Image
import org.d3if3111.assessmentmobpro.ui.image.ui.main.ImageAdapter

class ImageFragment : Fragment()  {

    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }

    private val viewModel: ImageViewModel by lazy {
        ViewModelProvider(this)[ImageViewModel::class.java]
    }

    private lateinit var binding: FragmentImageBinding
    private lateinit var myAdapter: ImageAdapter
    private var isLinearLayout = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        myAdapter = ImageAdapter()

        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) {
            isLinearLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }

    private fun setLayout() {
        binding.recyclerView.layoutManager = if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)
    }

    private fun setIcon(menuItem: MenuItem) {
        val iconId = if (isLinearLayout)
            R.drawable.ic_grid_layout
        else
            R.drawable.baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_layout) {
            lifecycleScope.launch {
                layoutDataStore.saveLayout(!isLinearLayout, requireContext())
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}