package com.anabell.words.ui.fragment.profile

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.anabell.words.R
import com.anabell.words.databinding.FragmentProfileBinding
import com.anabell.words.ui.auth.AuthCheckerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ProfileFragment : Fragment() {

    private lateinit var uri: Uri
    private lateinit var viewBinding: FragmentProfileBinding

    private val viewModel by viewModel<ProfileViewModel>()

    private val permissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::handlePermissionResult
    )

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ::handleGalleryResult
    )

    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicture(),
        ::handleCameraResult
    )

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

        viewBinding.editProfile.setOnClickListener {
            if (isReadExternalStoragePermissionGranted() && isCameraPermissionGranted() && isWriteExternalStoragePermissionGranted()) {
                chooseImageDialog()
            } else {
                requestPermission()
            }
        }

        viewBinding.logoutButton.setOnClickListener {
            logout()
        }

        viewModel.getProfileData()

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

    private fun handlePermissionResult(permissionResult: Map<String, Boolean>) {
        if (permissionResult.values.all { it }) {
            Toast.makeText(requireContext(), "Permission diterima", Toast.LENGTH_SHORT).show()
//            chooseImageDialog()
        } else {
            Toast.makeText(requireContext(), "Permission ditolak", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }

    private fun handleGalleryResult(result: Uri?) {
        viewBinding.editProfile.load(result)
    }

    private fun handleCameraResult(result: Boolean) {
        if (result) {
            viewBinding.editProfile.load(uri)
        }
    }

    private fun requestPermission() {
        permissionRequest.launch(
            arrayOf(
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE,
                CAMERA
            )
        )
    }

    private fun isReadExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            READ_EXTERNAL_STORAGE
        ) == PERMISSION_GRANTED
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            CAMERA
        ) == PERMISSION_GRANTED
    }

    private fun isWriteExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            WRITE_EXTERNAL_STORAGE
        ) == PERMISSION_GRANTED
    }

    private fun backToPreviousFragment() {
        val host: NavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment
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

    private fun chooseImageDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.pilih_gambar))
            .setItems(arrayOf(getString(R.string.kamera), getString(R.string.galeri))) { _, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            .show()
    }


    private fun openGallery() {
        activity?.intent?.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            "Words_",
            ".jpg",
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
        uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        cameraResult.launch(uri)
    }


}