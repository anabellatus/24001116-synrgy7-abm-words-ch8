package com.anabell.words.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anabell.words.MyApplication
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.databinding.FragmentListBinding
import com.anabell.words.ui.categorygadgetrecyclerview.CategoryAdapter
import com.anabell.words.ui.categorygadgetrecyclerview.CategoryAdapterListener


class ListFragment : Fragment(), CategoryAdapterListener {

    private lateinit var viewBinding: FragmentListBinding
    private val categoryAdapter by lazy { CategoryAdapter(this) }

    private val viewModel: ListViewModel by viewModels<ListViewModel>() {
        (activity?.application as MyApplication).viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view.context)
        refreshData()

        viewBinding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }
    }

    private fun goToDetailFragment(data: CategoryGadget) {
        val actionToFragmentDetail =
            ListFragmentDirections.actionListFragmentToDetailFragment()
        actionToFragmentDetail.name = data.name
        findNavController().navigate(actionToFragmentDetail)
    }

    override fun onClickCategory(data: CategoryGadget) {
        goToDetailFragment(data)
    }

    private fun setupRecyclerView(context: Context) {
        viewBinding.recyclerCategory.adapter = categoryAdapter
        viewBinding.recyclerCategory.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        viewBinding.recyclerCategory.itemAnimator = DefaultItemAnimator()

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.submitList(categories)
            viewBinding.swipeRefresh.isRefreshing = false
        }
    }

    private fun refreshData() {
        viewModel.retrieveCategoryData()
    }

}