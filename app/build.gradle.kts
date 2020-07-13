plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.hilt)
}

android {
    buildTypes {
        getByName("release").apply {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dynamicFeatures = mutableSetOf(":feature_albums", ":feature_favorite")
}

dependencies {
    api(project(":core"))
    implementation(project(":feature_profile"))

    addCommonLibraryDependencies()
    addCommonTestDependencies()
}

kapt {
    correctErrorTypes = true
}