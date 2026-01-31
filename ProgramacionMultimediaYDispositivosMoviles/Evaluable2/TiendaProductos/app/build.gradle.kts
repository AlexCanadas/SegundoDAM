plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.tiendaevaluable2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tiendaevaluable2"
        minSdk = 25
        targetSdk = 35  // Actualizado a 35 para cumplir con las dependencias
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material.v190)
    implementation(libs.androidx.constraintlayout.v214)
    implementation(libs.volley)


    // Dependencia de Picasso para cargar imágenes
    implementation(libs.picasso)

    // Dependencia de RecyclerView
    implementation(libs.androidx.recyclerview)

    // Dependencia de Activity (necesaria para algunas APIs de AndroidX)
    implementation(libs.androidx.activity.v1100)

    // Dependencia de Analytics (según tu configuración de libs)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.androidx.material3.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}
