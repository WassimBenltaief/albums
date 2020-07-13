plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

dependencies {
    //apply(plugin = "java")
    addCommonLibraryDependencies()
    addCommonTestDependencies()
}

kapt {
    correctErrorTypes = true
}