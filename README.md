1. buildFeatures {
        dataBinding = true
        viewBinding = true

    }

*** databinding still requires kapt


2. add ksp
To top level build.gradle.kts file.
plugins {
id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}


To module-level build.gradle.kts file
plugins{
id("com.google.devtools.ksp")
}

dependencies {

  // nvigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.7")

  //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")

  //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

  //Glide
    implementation("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

  //room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

}

3. Add safeArgs
To top level build.gradle file
def nav_version = "2.3.0"
classpath “androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version”
//OR
buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

To app level build.gradle file
apply plugin(androidx.navigation.safeargs.kotlin)
//OR

   id("androidx.navigation.safeargs.kotlin")
