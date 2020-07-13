plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

dependencies {
    addCommonLibraryDependencies()
    addCommonTestDependencies()
}

kapt {
    correctErrorTypes = true
}