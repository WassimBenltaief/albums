plugins {
    id(BuildPlugins.dynamicFeature)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
    id(BuildPlugins.hilt)
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
    testImplementation(project(":testShared"))

    addCommonLibraryDependencies()
    addCommonTestDependencies()
}

kapt {
    correctErrorTypes = true
}