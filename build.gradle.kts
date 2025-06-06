// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    // Plugin for using Kotlin Symbol Processing (KSP) – used by Room compiler
    // id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    kotlin("kapt") version "2.1.21" // replaced with kapt
}