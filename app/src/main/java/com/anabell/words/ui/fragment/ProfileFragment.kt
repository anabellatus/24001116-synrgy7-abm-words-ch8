package com.anabell.words.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anabell.words.databinding.FragmentProfileBinding
import com.anabell.words.ui.MainActivity
import kotlin.math.log

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

    }

    private fun logout() {
        viewModel.logout()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}