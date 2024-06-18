plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.ilharper.sym"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ilharper.sym"
        minSdk = 21
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
                "proguard-rules.pro",
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    viewBinding {
        enable = true
    }

    dataBinding {
        enable = true
    }
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.coordinatorlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)
    implementation(libs.androidx.viewpager)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.preference)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.java8)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.lifecycle.reactivestreams)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.rxjava3)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.features.fragment)

    implementation(libs.hilt.runtime)
    kapt(libs.hilt.compiler)

    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.okio)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)

    implementation(libs.coil.base)
    implementation(libs.coil.gif)
    implementation(libs.coil.svg)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.rxkotlin)
    implementation(libs.reactivestreams)

    implementation(libs.blurview)

    implementation(libs.apache.commons.lang)
    implementation(libs.jsoup)
    implementation(libs.avatarimagegenerator)

    testImplementation(libs.androidx.lifecycle.testing)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.paging.common)

    testImplementation(libs.junit)
    testImplementation(libs.hilt.testing)
    kaptTest(libs.hilt.compiler)
    testImplementation(libs.coil.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compiler)
}
