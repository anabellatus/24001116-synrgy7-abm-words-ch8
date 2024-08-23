package com.anabell.words.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.anabell.words.R
import com.anabell.words.databinding.FragmentProfileBinding
import com.anabell.words.ui.auth.AuthCheckerActivity

class ProfileFragment : Fragment() {

    private lateinit var viewBinding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModel>() {
        ProfileViewModel.provideFactory(this, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentProfileBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.logoutButton.setOnClickListener {
            logout()
        }

        viewModel.getProfileData()
        viewModel.userName.observe(viewLifecycleOwner) { userName ->
            viewBinding.tvName.text = userName
        }
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            viewBinding.tvEmail.text = userEmail
        }

        viewBinding.favoriteListButton.setOnClickListener {
            handleNavigateToFavorite()
        }

        viewBinding.backButton.setOnClickListener {
            backToPreviousFragment()
        }

    }

    private fun backToPreviousFragment() {
        val host : NavHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment
        host.navController.navigateUp()
    }

    private fun logout() {
        viewModel.logout()
        val intent = Intent(requireActivity(), AuthCheckerActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun handleNavigateToFavorite() {
        val actionToFragmentFavorite =
            ProfileFragmentDirections.actionProfileFragmentToFavoriteFragment()
        findNavController().navigate(actionToFragmentFavorite)
    }

}