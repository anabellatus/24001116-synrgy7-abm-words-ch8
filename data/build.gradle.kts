plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.anabell.words.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL_GADGET", "\"https://my-json-server.typicode.com/anabellatus/db/\"")
        }
        create("staging") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL_GADGET", "\"https://my-json-server.typicode.com/anabellatus/db/\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL_GADGET", "\"https://my-json-server.typicode.com/anabellatus/db/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.androidx.work.runtime.ktx)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
}