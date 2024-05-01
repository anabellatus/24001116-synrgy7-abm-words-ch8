package com.anabell.words.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anabell.words.categorygadgetrecyclerview.CategoryAdapter
import com.anabell.words.categorygadgetrecyclerview.CategoryAdapterListener
import com.anabell.words.categorygadgetrecyclerview.CategoryGadget
import com.anabell.words.databinding.FragmentListBinding


class ListFragment : Fragment(), CategoryAdapterListener {

    private lateinit var viewBinding: FragmentListBinding
    private val categoryAdapter by lazy { CategoryAdapter(this) }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recyclerCategory.adapter = categoryAdapter
        viewBinding.recyclerCategory.layoutManager = LinearLayoutManager(
            view.context,
            RecyclerView.VERTICAL,
            false
        )

        viewBinding.recyclerCategory.itemAnimator = DefaultItemAnimator()
        categoryAdapter.submitList(retrieveCategoryData())

        viewBinding.swipeRefresh.setOnRefreshListener{
            categoryAdapter.submitList(retrieveCategoryData())
            viewBinding.swipeRefresh.isRefreshing = false
        }

    }

    private fun goToDetailFragment(data: CategoryGadget){
        val actionToFragmentDetail = ListFragmentDirections.actionListFragmentToDetailFragment()
        actionToFragmentDetail.name = data.name
        findNavController().navigate(actionToFragmentDetail)
    }

    override fun onClickCategory(data: CategoryGadget) {
        goToDetailFragment(data)
    }

    private fun retrieveCategoryData(): List<CategoryGadget>{
        return listOf(
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/3437/3437364.png",
                name = "Smartphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2888/2888704.png",
                name = "Laptop",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/564/564579.png",
                name = "Tablet",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5906/5906114.png",
                name = "Earphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2668/2668914.png",
                name = "Camera",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/8462/8462356.png",
                name = "Speaker",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/617/617694.png",
                name = "Smartwatch",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/771/771261.png",
                name = "Game Console",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5606/5606227.png",
                name = "TV",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/6212/6212141.png",
                name = "Tools",
            ),
        )
    }

}