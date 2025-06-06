plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // id("com.google.devtools.ksp") // Enables KSP support for annotation processing
    id("kotlin-kapt") // replaced with ksp
}

android {
    namespace = "com.example.managemate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.managemate"
        minSdk = 27
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /*
    ROOM DB:
    Room is a library from Android Jetpack that makes it easy to store, retrieve, and manage data
    in a local SQLite database using Kotlin or Java

    KSP:
    KSP (Kotlin Symbol Processing) is a tool used to generate code at compile time in Kotlin.
    In simple words:
    KSP reads your annotations (like @Entity, @Dao) and auto-generates the necessary code
    (like Room database classes), so you don’t have to write it manually.
    It’s faster and more Kotlin-friendly than the older kapt (Kotlin Annotation Processing Tool)
    */

    val room_version = "2.7.1"

    // Room runtime – core database functionality
    implementation("androidx.room:room-runtime:$room_version")

    // Room compiler – generates Room boilerplate code using KSP
    // ksp("androidx.room:room-compiler:$room_version")
    // Room compiler with kapt (KSP doesn't support DataBinding yet)
    kapt("androidx.room:room-compiler:$room_version") // replaced with ksp

    // Room Kotlin Extensions – provides coroutine support and other Kotlin-friendly APIs
    implementation("androidx.room:room-ktx:$room_version")

    // Coroutines – helps run database operations in the background (e.g. inside ViewModel)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    val lifecycle_version = "2.9.1"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")


}