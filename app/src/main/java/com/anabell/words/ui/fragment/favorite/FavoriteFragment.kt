package com.anabell.words.ui.fragment.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.anabell.words.domain.model.Gadget
import com.anabell.words.databinding.FragmentFavoriteBinding
import com.anabell.words.ui.gadgetrecycler.GadgetAdapter
import com.anabell.words.ui.gadgetrecycler.GadgetAdapterListener

class FavoriteFragment : Fragment(), GadgetAdapterListener {

    private lateinit var viewBinding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.provideFactory(this, requireContext())
    }
    private val gadgetAdapter by lazy { GadgetAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFavoriteBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view.context)

        viewModel.loadAllFavoriteGadget()

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                viewBinding.progressBar.visibility = View.VISIBLE
            } else {
                viewBinding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView(context: Context) {
        viewBinding.recyclerGadget.layoutManager = GridLayoutManager(context, 2)
        viewBinding.recyclerGadget.adapter = gadgetAdapter

        viewBinding.recyclerGadget.itemAnimator = DefaultItemAnimator()

        viewModel.gadgets.observe(viewLifecycleOwner) { gadgets ->
            Log.d("FavoriteFragment", "gadgets: $gadgets")
            gadgetAdapter.submitList(gadgets)
            viewBinding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onClickGadget(data: Gadget) {
        // Do nothing
    }
}