const val kotlinVersion = "1.3.72"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.0"
        const val safeArgs = "2.3.0"
        const val hilt = "2.28-alpha"
    }

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val dynamicFeature = "com.android.dynamic-feature"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

    const val kotlinKapt = "kotlin-kapt"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val hilt = "dagger.hilt.android.plugin"

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object AppConfig {
    const val minSdk = 22
    const val compileSdk = 29
    const val targetSdk = 29
    const val buildToolsVersion = "30.0.0"
    const val name = "com.wassim.showcase"
    const val code = 1
    const val version = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.0.0-beta01"
        const val constraintLayout = "1.1.2"
        const val ktlint = "0.36.0"
        const val androidxCore = "1.3.0"
        const val androidxNavigation = "2.3.0"
        const val androidxLifecycleViewModel = "2.3.0-alpha05"
        const val retrofit = "2.9.0"
        const val retrofitGsonConverter = "2.7.2"
        const val loggingInterceptor = "4.7.2"
        const val gson = "2.8.6"
        const val timber = "4.7.1"
        const val coil = "0.11.0"
        const val playCore = "1.7.3"
        const val material = "1.1.0"
        const val hilt = "2.28-alpha"
        const val hiltViewModel = "1.0.0-alpha01"
        const val hiltCompiler = "1.0.0-alpha01"
        const val room = "2.2.5"
    }

    /**
     * Kotlin
     */
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    /**
     * Android x
     */
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val androidXNavigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}"
    const val androidXNavigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"
    const val androidXNavigationDynamicFeatures =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.androidxNavigation}"
    const val androidxLifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycleViewModel}"

    /**
     * Networking
     */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonConverter}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    /**
     * utilities
     */
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val playCore = "com.google.android.play:core:${Versions.playCore}"

    /**
     * design
     */
    const val material = "com.google.android.material:material:${Versions.material}"

    /**
     * DI
     */
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"

    /**
     * Database
     **/
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    /**
     * Tools
     */
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}

object TestDependencies {
    private object Versions {
        const val junit4 = "4.13"
        const val mockk = "1.10.0"
        const val androidxTests = "1.2.0"
        const val archCoreTest = "2.1.0"
        const val fragmentTesting = "1.2.5"
        const val extensionJunit = "1.1.1"
        const val espresso = "3.2.0"
        const val coroutinesTest = "1.3.7"
        const val hiltTesting = "2.28-alpha"
        const val room = "2.2.5"
        const val lifecycleLivedataKtx = "2.2.0"
    }

    /**
     * junit
     **/
    const val junit = "junit:junit:${Versions.junit4}"

    /**
     * Mocking
     **/
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    /**
     * Android X
     **/
    const val testRunner = "androidx.test:runner:${Versions.androidxTests}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    const val testCore = "androidx.test:core:${Versions.androidxTests}"
    const val testRules = "androidx.test:rules:${Versions.androidxTests}"
    const val lifecycleLivedataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedataKtx}"


    const val extensionJunit = "androidx.test.ext:junit:${Versions.extensionJunit}"
    const val extensionTruth = "androidx.test.ext:truth:${Versions.androidxTests}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espressoIdlingConcurrent =
        "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"
    const val espressoIdlingResource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"

    /**
     * Coroutines
     **/
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

    /**
     * DI
     */
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltTesting}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltTesting}"

    /**
     * Database
     **/
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"

}