import org.gradle.api.JavaVersion

object AndroidConfig {

    // Android SDK
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = compileSdk

    // Compatibility
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    // JvmTarget
    const val jvmTarget = "1.8"

    // Version
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {

    // Kotlin
    const val kotlin = "1.6.10"

    // Core
    const val core_ktx = "1.7.0"
    const val app_compat = "1.4.1"
    const val material = "1.5.0"

    // Compose
    const val compose = "1.1.1"
    const val compose_material3 = "1.0.0-alpha10"
    const val activity_compose = "1.4.0"
    const val accompanist = "0.23.1"
    const val navigation = "2.4.1"

    // Lifecycle
    const val lifecycle = "2.4.1"

    // Coroutines
    const val coroutines = "1.6.0"

    // Splash Screen
    const val splashscreen = "1.0.0-beta02"

    // Hilt
    const val hilt = "2.41"
    const val hilt_compiler = "1.0.0"
    const val hilt_navigation = "1.0.0"

    // Kotlinx Serialization
    const val kotlinx_serialization = "1.3.2"

    // Test
    const val junit = "4.13.2"
    const val junit_android = "1.1.3"
    const val truth = "1.1.3"
    const val arch_core = "2.1.0"
    const val mockk = "1.12.3"
}

object Libs {
    // Core
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // Compose
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val compose_material3 =
        "androidx.compose.material3:material3:${Versions.compose_material3}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val compose_icons = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val compose_icons_extended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    // Splash Screen
    const val splashscreen = "androidx.core:core-splashscreen:${Versions.splashscreen}"

    // Lifecycle
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val viewModel_compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"

    // SystemUiController
    const val systemuicontroller =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

    // Coroutine
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Kotlin Serialization
    const val kotlinx_serialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx_serialization}"

    // Hilt
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_compiler_dagger_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hilt_compose_navigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation}"
    const val hilt_compiler_android_kapt = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler}"

    // Permission
    const val permission = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockk_jvm = "io.mockk:mockk-agent-jvm:${Versions.mockk}"
}

object AndroidTestLibs {
    const val junit = "androidx.test.ext:junit:${Versions.junit_android}"
    const val junit_compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val debug_compose_ui = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val arch_core = "androidx.arch.core:core-testing:${Versions.arch_core}"
}
