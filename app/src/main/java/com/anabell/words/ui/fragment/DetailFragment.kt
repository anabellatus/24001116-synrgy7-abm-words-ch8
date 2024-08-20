package com.anabell.words.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.anabell.words.databinding.FragmentDetailBinding
import com.anabell.words.ui.gadgetrecycler.Gadget
import com.anabell.words.ui.gadgetrecycler.GadgetAdapter
import com.anabell.words.ui.gadgetrecycler.GadgetAdapterListener

class DetailFragment : Fragment(), GadgetAdapterListener {

    private lateinit var viewBinding: FragmentDetailBinding
    private val gadgetAdapter by lazy { GadgetAdapter(this) }

    private val viewModel: DetailViewModel by viewModels<DetailViewModel> {
        DetailViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentDetailBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view.context)
        refreshData()

        viewModel.setCategoryName(getCategoryName())

        viewBinding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun setupRecyclerView(context: Context) {
        viewBinding.recyclerGadget.layoutManager = GridLayoutManager(context, 2)
        viewBinding.recyclerGadget.adapter = gadgetAdapter

        viewBinding.recyclerGadget.itemAnimator = DefaultItemAnimator()

        viewModel.gadgets.observe(viewLifecycleOwner) { gadgets ->
            gadgetAdapter.submitList(viewModel.filterGadgetByCategory(gadgets, getCategoryName()))
            viewBinding.swipeRefresh.isRefreshing = false
        }
    }

    private fun refreshData() {
        viewModel.retrieveGadgetData()
    }

    private fun getCategoryName(): String {
        return getArgs().name
    }

    private fun getArgs(): com.anabell.words.ui.fragment.DetailFragmentArgs {
        return com.anabell.words.ui.fragment.DetailFragmentArgs.fromBundle(arguments as Bundle)
    }

    private fun handleNavigateToGoogle(name: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(viewModel.getUrl(name))
        startActivity(urlIntent)
    }

    override fun onClickGadget(data: Gadget) {
        handleNavigateToGoogle(data.name)
    }
}