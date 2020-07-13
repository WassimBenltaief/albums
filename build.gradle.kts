import com.android.build.gradle.DynamicFeaturePlugin
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.safeArgsGradlePlugin)
        classpath(BuildPlugins.hiltGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

/**
 * configure all sub-projects by applying
 * common declaration blocks
 */
subprojects {
    plugins.whenPluginAdded {
        // add common declarations
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<BaseAppModuleExtension>()
                    .apply {
                        applyCommons()
                        applyAdditionalConfig()
                    }
            }
            is DynamicFeaturePlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        applyCommons()
                    }
            }
            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        applyCommons()
                        applyProguard()
                    }
            }
        }
    }
}

/**
 * Common Application level extensions
 */
fun BaseExtension.applyCommons() {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig.apply {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.code
        versionName = AppConfig.version
        testInstrumentationRunner = AppConfig.testRunner
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

fun LibraryExtension.applyProguard() {
    defaultConfig.apply {
        consumerProguardFile("consumer-rules.pro")
    }
}

/**
 * Common Libraries level extensions
 */
fun AppExtension.applyAdditionalConfig() {
    defaultConfig.apply {
        applicationId = AppConfig.name
        vectorDrawables.useSupportLibrary = true
    }
}

/**
 * Delete all build files on task clean
 */

tasks {
    val clean by registering(Delete::class) {
        println("deleting build folder")
        delete(buildDir)
    }
}