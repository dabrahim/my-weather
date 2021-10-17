object Dependencies {
    const val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // GRADLE
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"

    // KOTLIN
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // HILT
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    // OkHttp Login interceptor
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"

    // RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    // RXJAVA 2
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    // Android Navigation
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
    const val navigationSafeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"

    // GLIDE
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}