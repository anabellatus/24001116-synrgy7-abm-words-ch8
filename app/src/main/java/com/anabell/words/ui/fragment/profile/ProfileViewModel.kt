package com.anabell.words.ui.fragment.profile

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.anabell.words.R
import com.anabell.words.domain.repository.AuthRepository
import com.anabell.words.ui.fragment.profile.ProfileFragment.Companion.KEY_IMAGE_URI
import com.anabell.words.worker.BlurWorker
import com.anabell.words.worker.CleanupWorker
import com.anabell.words.worker.SaveImageToFileWorker
import com.anabell.words.worker.TAG_OUTPUT
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    application: Application,
) : ViewModel() {

    companion object {
        private const val IMAGE_MANIPULATION_WORK_NAME = "image_blurring_work"
    }

    private var imageUri: Uri? = null
    private var outputUri: Uri? = null

    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    init {
        imageUri = getImageUri(application.applicationContext)
    }

    internal fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    internal fun applyBlur() {
        var continuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()

        blurBuilder.setInputData(createInputDataForUri())

        continuation = continuation.then(blurBuilder.build())

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        continuation = continuation.then(save)

        continuation.enqueue()
    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }

    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.ic_launcher_background))
            .appendPath(resources.getResourceTypeName(R.drawable.ic_launcher_background))
            .appendPath(resources.getResourceEntryName(R.drawable.ic_launcher_background))
            .build()
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }

    fun setImageUri(imageUri: Uri) {
        this.imageUri = imageUri
    }

    fun getProfileData() {
        viewModelScope.launch {
            try {
//                _userName.value = authRepository.loadUserName()
                _userEmail.value = authRepository.loadUserEmail()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }

    fun logout() {
        viewModelScope.launch {
            try {
                authRepository.clearToken()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }

    }
}