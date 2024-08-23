package com.anabell.words.ui.fragment.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.anabell.words.databinding.FragmentDetailBinding
import com.anabell.words.data.model.Gadget
import com.anabell.words.ui.gadgetrecycler.GadgetAdapter
import com.anabell.words.ui.gadgetrecycler.GadgetAdapterListener

class DetailFragment : Fragment(), GadgetAdapterListener {

    private lateinit var viewBinding: FragmentDetailBinding
    private val gadgetAdapter by lazy { GadgetAdapter(this) }

    private val viewModel: DetailViewModel by viewModels<DetailViewModel> {
        DetailViewModel.provideFactory(this, requireContext())
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

//        viewModel.error.observe(viewLifecycleOwner) { error ->
//            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
//        }

        setupRecyclerView(view.context)
        refreshData()

        viewModel.setCategoryName(getCategoryName())

        viewBinding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }

//        viewBinding.profileButton.setOnClickListener {
//            goToProfileFragment()
//        }
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
//        viewModel.loadGadgetsFromFavorite()
    }

    private fun getCategoryName(): String {
        return getArgs().name
    }

    private fun goToProfileFragment() {
//        val actionToFragmentProfile =
//            DetailFragmentDirections.actionDetailFragmentToProfileFragment()
//        findNavController().navigate(actionToFragmentProfile)
    }

    private fun getArgs(): DetailFragmentArgs {
        return DetailFragmentArgs.fromBundle(arguments as Bundle)
    }

    private fun handleNavigateToGoogle(name: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(viewModel.getUrl(name))
        startActivity(urlIntent)
    }

    private fun handleNavigateToDetailGadget(data: Gadget) {
        val actionToFragmentDetailGadget =
            DetailFragmentDirections.actionDetailFragmentToDetailGadgetFragment()
        actionToFragmentDetailGadget.name = data.name
        actionToFragmentDetailGadget.image = data.image
        actionToFragmentDetailGadget.price = data.price
        actionToFragmentDetailGadget.id = data.id
        actionToFragmentDetailGadget.category = data.category
        findNavController().navigate(actionToFragmentDetailGadget)
    }

    override fun onClickGadget(data: Gadget) {
        handleNavigateToDetailGadget(data)
//        handleNavigateToGoogle(data.name)
    }

//    override fun onAddToFavorite(data: Gadget) {
//        viewModel.addGadgetToFavorites(
//            data.image,
//            data.name,
//            data.category,
//            data.price,
//            data.id
//        )
//        data.isFavorite = true
//        Toast.makeText(context, "${data.name} ditambahkan ke favorites", Toast.LENGTH_SHORT).show()
//        refreshData()
//    }
//
//    override fun onRemoveFromFavorite(data: Gadget) {
//        Log.d("DetailFragment", "onRemoveFromFavorite: $data")
//        viewModel.removeGadgetFromFavorites(data)
//        data.isFavorite = false
//        Toast.makeText(context, "${data.name} dihapus dari favorites", Toast.LENGTH_SHORT).show()
//        refreshData()
//    }

}