plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt.android.gradle.plugin)
}

android {
    compileSdk = 34
    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas".toString())
        }
    }

    namespace = "com.viplearner.budgetmanager.data"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.jakewharton.timber)
    api(libs.androidx.room.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

}

