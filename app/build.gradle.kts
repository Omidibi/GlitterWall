plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.omid.glitterwall"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.omid.glitterwall"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

        buildFeatures {
            viewBinding = true
            dataBinding = true
        }

        kapt {
            correctErrorTypes = true
        }
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.11.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.picasso:picasso:2.71828")
        implementation("androidx.multidex:multidex:2.0.1")
        implementation("com.romandanylyk:pageindicatorview:1.0.3")
        implementation("com.github.bumptech.glide:glide:4.16.0")
        implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
        val roomVersion = "2.6.1"
        implementation("androidx.room:room-runtime:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")
        implementation("com.google.dagger:hilt-android:2.44")
        kapt("com.google.dagger:hilt-android-compiler:2.44")
        implementation("io.coil-kt:coil:1.4.0")
        implementation("com.google.code.gson:gson:2.10")
        implementation("com.squareup.okhttp3:okhttp:4.12.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
        implementation("io.reactivex.rxjava3:rxjava:3.1.5")
        implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
        val navVersion = "2.7.7"
        implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
        implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
        /*implementation("com.google.dagger:dagger-android:2.48.1")
        kapt("com.google.dagger:dagger-compiler:2.48.1")*/
    }
}