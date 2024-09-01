package com.anabell.words.ui.fragment.detailgadget

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.anabell.words.R
import com.anabell.words.databinding.FragmentDetailGadgetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGadgetFragment : Fragment() {

    private lateinit var viewBinding: FragmentDetailGadgetBinding

    private val viewModel by viewModel<DetailGadgetViewModel>()

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

        viewModel.loadGadgetsFromFavorite(getArgsId())


        viewModel.isFavorite.observe(viewLifecycleOwner) { v ->
            if (v) {
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
                viewBinding.favoriteButton.setOnClickListener {
                    viewModel.removeGadgetFromFavorites()
                }
            } else {
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
                viewBinding.favoriteButton.setOnClickListener {
                    viewModel.addGadgetToFavorites(
                        image = getImage(),
                        name = getName(),
                        category = getCategory(),
                        price = getPrice(),
                        id = getArgsId()
                    )
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, "error : " + error.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        viewModel.insertGadget.observe(viewLifecycleOwner) { insert ->
            if (insert) {
                Toast.makeText(context, getString(R.string.add_favorite_success), Toast.LENGTH_SHORT)
                    .show()
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_fill_24)
            }
        }

        viewModel.deleteGadget.observe(viewLifecycleOwner) { delete ->
            if (delete) {
                Toast.makeText(context,
                    getString(R.string.delete_favorite_success), Toast.LENGTH_SHORT).show()
                viewBinding.favoriteButton.setImageResource(R.drawable.favorite_border_24)
            }
        }

        viewBinding.imageProduct.load(getImage())
        viewBinding.tvProductName.text = getName()
        viewBinding.tvProductPrice.text = getPrice().toString()
        viewModel.setCategoryName(getCategory())

        viewBinding.profileButton.setOnClickListener {
            goToProfileFragment()
        }
    }

    private fun goToProfileFragment() {
        val actionToFragmentProfile =
            DetailGadgetFragmentDirections.actionDetailGadgetFragmentToProfileFragment()
        findNavController().navigate(actionToFragmentProfile)
    }

    private fun handleNavigateToGoogle(name: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW)
        urlIntent.data = Uri.parse(viewModel.getUrl(name))
        startActivity(urlIntent)
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