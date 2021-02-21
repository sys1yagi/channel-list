package com.sys1yagi.channel_list

object Versions {
    const val ktlint = "0.39.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha07"
    const val junit = "junit:junit:4.13.2"
    const val junit5 = "org.junit.jupiter:junit-jupiter:5.5.2"
    const val material = "com.google.android.material:material:1.3.0"
    const val versionPlugin = "com.github.ben-manes:gradle-versions-plugin:0.36.0"


    object Google {
        const val googleService = "com.google.gms:google-services:4.3.4"
        const val apiClient = "com.google.api-client:google-api-client-android:1.30.11"
        const val youtube = "com.google.apis:google-api-services-youtube:v3-rev20200618-1.30.9"
        const val oauth2 = "com.google.auth:google-auth-library-oauth2-http:0.22.0"
    }

    object GooglePlay {
        const val auth = "com.google.android.gms:play-services-auth:18.1.0"
    }

    object Accompanist {
        private const val version = "0.5.1"
        const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
    }

    object Kotlin {
        private const val version = "1.4.30"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object DI {
        private const val version = "2.2.0"
        val core = "org.koin:koin-core:$version"
        val coreExt = "org.koin:koin-core-ext:$version"
        val android = "org.koin:koin-android:$version"
        val androidxScope = "org.koin:koin-androidx-scope:$version"
        val androidxViewModel = "org.koin:koin-androidx-viewmodel:$version"

        //        val androidxCompose = "org.koin:koin-androidx-compose:2.2.0-beta-2"
        val androidxExt = "org.koin:koin-androidx-ext:$version"
    }

    object Database {
        const val version = "2.2.5"

        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.5.0-beta01"
        const val multiDex = "androidx.multidex:multidex:2.0.1"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0-beta01"
        const val activity = "androidx.activity:activity-ktx:1.3.0-alpha02"

        object Compose {
            const val snapshot = ""
            const val version = "1.0.0-alpha12"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha07"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha01"
            const val activity = "androidx.activity:activity-compose:1.3.0-alpha02"
            const val uiTest = "androidx.compose.ui:ui-test:$version"
        }

        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"


            object Ext {
                private const val extVersion = "1.1.3-alpha04"
                const val junit = "androidx.test.ext:junit-ktx:$extVersion"
                const val truth = "androidx.test.ext:truth:1.4.0-alpha04"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0-alpha04"
        }
    }

    object Preference {
        const val kotPref = "com.chibatching.kotpref:kotpref:2.12.0"
    }
}
