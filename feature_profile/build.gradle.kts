plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.hilt)
}

dependencies {
    addCommonLibraryDependencies()
    addCommonTestDependencies()
}

kapt {
    correctErrorTypes = true
}
