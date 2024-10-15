plugins {
    alias(libs.plugins.android.application) // Use the alias for AGP
    alias(libs.plugins.jetbrains.kotlin.android) // Use the alias for Kotlin
}

android {
    namespace = "com.example.test01"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test01"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true // Correct view binding syntax
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx) // androidx.core:core-ktx
    implementation(libs.androidx.appcompat) // androidx.appcompat:appcompat
    implementation(libs.material) // com.google.android.material:material
    implementation(libs.androidx.activity) // androidx.activity:activity
    implementation(libs.androidx.constraintlayout) // androidx.constraintlayout:constraintlayout

    testImplementation(libs.junit) // junit:junit
    androidTestImplementation(libs.androidx.junit) // androidx.test.ext:junit
    androidTestImplementation(libs.androidx.espresso.core) // androidx.test.espresso:espresso-core
}
