import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addCommonLibraryDependencies() {
    /**
     * Kotlin
     */
    implementation(Libraries.kotlinStdLib)

    /**
     * androidx
     */
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxAppCompat)
    implementation(Libraries.androidxConstraintLayout)
    implementation(Libraries.androidXNavigationUi)
    implementation(Libraries.androidXNavigationFragment)
    implementation(Libraries.androidXNavigationDynamicFeatures)
    implementation(Libraries.androidxLifecycleViewModel)

    /**
     * networking
     */
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.gson)

    /**
     * utilities
     */
    implementation(Libraries.timber)
    implementation(Libraries.coil)
    implementation(Libraries.playCore)

    /**
     * design
     */
    implementation(Libraries.material)

    /**
     * Dependency injection
     */
    implementation(Libraries.hilt)
    implementation(Libraries.hiltViewModel)
    kapt(Libraries.hiltCompiler)
    kapt(Libraries.daggerHiltCompiler)

    /**
     * Database
     */
    implementation(Libraries.room)
    implementation(Libraries.roomKtx)
    kapt(Libraries.roomCompiler)

}

fun DependencyHandler.addCommonTestDependencies() {

    /**
     * Junit
     */
    testImplementation(TestDependencies.junit)

    /**
     * Mocking
     **/
    testImplementation(TestDependencies.mockk)

    /**
     * Android X
     **/
    testImplementation(TestDependencies.testRunner)
    testImplementation(TestDependencies.archCoreTest)
    androidTestImplementation(TestDependencies.fragmentTesting)
    androidTestImplementation(TestDependencies.testCore)
    androidTestImplementation(TestDependencies.testRules)
    androidTestImplementation(TestDependencies.extensionJunit)
    androidTestImplementation(TestDependencies.extensionTruth)
    androidTestImplementation(TestDependencies.espressoCore)
    androidTestImplementation(TestDependencies.espressoContrib)
    androidTestImplementation(TestDependencies.espressoIdlingConcurrent)
    androidTestImplementation(TestDependencies.espressoIdlingResource)

    /**
     * Coroutines
     **/
    testImplementation(TestDependencies.coroutinesTest)
    androidTestImplementation(TestDependencies.coroutinesTest)

    /**
     * DI
     **/
    testImplementation(TestDependencies.hiltTesting)
    kaptTest(TestDependencies.hiltAndroidCompiler)

    /**
     * Database
     **/
    testImplementation(TestDependencies.roomTesting)
    androidTestImplementation(TestDependencies.roomTesting)
}

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.kaptTest(dependencyNotation: Any): Dependency? =
    add("kaptTest", dependencyNotation)
