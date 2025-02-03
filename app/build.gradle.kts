plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.jeremieguillot.ecosnap"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jeremieguillot.ecosnap"
        minSdk = 26
        targetSdk = 35
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
    //packaging {
    //  exclude("META-INF/versions/9/OSGI-INF/MANIFEST.MF")
    //}
}

dependencies {

    // Camera module dependencies
    implementation(projects.camera.domain)
    implementation(projects.camera.data)
    implementation(projects.camera.presentation)

    // Home module dependencies
    implementation(projects.home.presentation)
    implementation(projects.home.data)
    implementation(projects.home.domain)

    // Cleaning module dependencies
    implementation(projects.cleaning.data)
    implementation(projects.cleaning.presentation)
    implementation(projects.cleaning.domain)

    // Core module dependencies
    implementation(projects.core.data)
    implementation(projects.core.presentation)
    implementation(projects.core.domain)

    // Core submodules
    implementation(projects.core.presentation.designsystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    //DI Koin
    implementation(libs.bundles.koin)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}