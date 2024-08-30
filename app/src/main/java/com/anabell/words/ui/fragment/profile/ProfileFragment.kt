package com.anabell.words.ui.fragment.profile

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import coil.load
import com.anabell.words.R
import com.anabell.words.databinding.FragmentProfileBinding
import com.anabell.words.ui.auth.AuthCheckerActivity
import com.nareshchocha.filepickerlibrary.models.PickMediaConfig
import com.nareshchocha.filepickerlibrary.models.PickMediaType
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    companion object {
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    }

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

    private val filePickerResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleFilePickerResult,
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
            chooseImageDialog()
        }

        viewBinding.blurButton.setOnClickListener {
            viewModel.applyBlur()
        }

        viewBinding.cancelButton.setOnClickListener {
            viewModel.cancelWork()
        }

        viewModel.outputWorkInfos.observe(viewLifecycleOwner, workInfosObserver())

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

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    viewBinding.editProfile.load(outputImageUri)
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    private fun showWorkFinished() {
        with(viewBinding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            blurButton.visibility = View.VISIBLE
        }
    }

    private fun showWorkInProgress() {
        with(viewBinding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            blurButton.visibility = View.GONE
        }
    }

    private fun handlePermissionResult(permissionResult: Map<String, Boolean>) {
        if (permissionResult.containsValue(false)) {
            Toast.makeText(requireContext(), "permission ditolak", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        } else {
            Toast.makeText(requireContext(), "permission diterima", Toast.LENGTH_SHORT).show()
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

    private fun handleFilePickerResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let {
                viewBinding.editProfile.load(it)
                viewModel.setImageUri(it)
            }
        } else {
            Log.d("ProfileFragment", "File Picker cancelled or failed")
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
                    1 -> openFilePicker()
                }
            }
            .show()
    }

    private fun openFilePicker() {
        filePickerResult.launch(
            FilePicker.Builder(requireContext())
                .pickMediaBuild(
                    PickMediaConfig(
                        mPickMediaType = PickMediaType.ImageOnly,
                        allowMultiple = true
                    )
                )
        )
    }

    private fun openCamera() {
        filePickerResult.launch(
            FilePicker.Builder(requireContext())
                .imageCaptureBuild()
        )
    }

}