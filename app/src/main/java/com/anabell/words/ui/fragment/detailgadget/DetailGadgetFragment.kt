package com.anabell.words.ui.fragment.detailgadget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.anabell.words.R
import com.anabell.words.databinding.FragmentDetailGadgetBinding

class DetailGadgetFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailGadgetBinding

    private val viewModel by viewModels<DetailGadgetViewModel> {
        DetailGadgetViewModel.provideFactory(this, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailGadgetBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gadgetLocal.observe(viewLifecycleOwner) { gadget ->
            if (gadget != null) {
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
                viewBinding.favoriteButton.setOnClickListener {
                    viewModel.removeGadgetFromFavorites(
                        gadget
                    )
                }
            } else {
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
                viewBinding.favoriteButton.setOnClickListener {
                    viewModel.addGadgetToFavorites(
                        image = getImage(),
                        name = getName(),
                        category = getCategory(),
                        price = getPrice(),
                    )
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error.message.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.insertGadget.observe(viewLifecycleOwner) { insert ->
            if (insert) {
                Toast.makeText(context, "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
            }
        }

        viewModel.deleteGadget.observe(viewLifecycleOwner) { delete ->
            if (delete) {
                Toast.makeText(context, "Berhasil dihapus dari favorit", Toast.LENGTH_SHORT).show()
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
            }
        }

        viewBinding.imageProduct.load(getImage())
        viewBinding.tvProductName.text = getName()
        viewBinding.tvProductPrice.text = getPrice().toString()
        viewModel.setCategoryName(getCategory())

        viewModel.loadGadgetsFromFavorite(getArgsId())

        viewBinding.profileButton.setOnClickListener {
            goToProfileFragment()
        }
    }

    private fun goToProfileFragment() {
        val actionToFragmentProfile =
            DetailGadgetFragmentDirections.actionDetailGadgetFragmentToProfileFragment()
        findNavController().navigate(actionToFragmentProfile)
    }

    private fun getName(): String {
        return getArgs().name
    }

    private fun getImage(): String {
        return getArgs().image
    }

    private fun getPrice(): Int {
        return getArgs().price
    }

    private fun getArgsId(): Int {
        return getArgs().id
    }

    private fun getCategory(): String {
        return getArgs().category
    }

    private fun getArgs(): DetailGadgetFragmentArgs {
        return DetailGadgetFragmentArgs.fromBundle(arguments as Bundle)
    }
}