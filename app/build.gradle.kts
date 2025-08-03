plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mr.anonym.discounts"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mr.anonym.discounts"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    ksp {
        arg("option_name", "option_value")
        arg("room.schemaLocation", "$projectDir/schemas")
        // other otions...
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

//    Modules
    implementation(project(":data"))
    implementation(project(":domain"))

    //    Dagger hilt
    implementation(libs.daggerHilt)
    implementation(libs.daggerHiltNavigationCompose)
    ksp(libs.daggerHiltCompiler)

    //    Room SQLite
    implementation(libs.room)
    implementation(libs.roomCoroutinesSupport)
    ksp(libs.roomCompiler)

    //    Jetpack navigation
    implementation(libs.navigation)

    //    Coroutines & lifecycle
    implementation(libs.kotlinCoroutines)
    implementation(libs.viewModel)
    implementation(libs.viewModelForCompose)
    implementation(libs.lifecycleCompose)
    implementation(libs.savedState)

    //    System UI controller
    implementation(libs.systemUiController)

    //    Lottie animations
    implementation(libs.lottieanimations)
}