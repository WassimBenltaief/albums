plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

dependencies {
    implementation(TestDependencies.junit)
    implementation(TestDependencies.coroutinesTest)
    implementation(TestDependencies.lifecycleLivedataKtx)
}
