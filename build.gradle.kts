import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
}

group = "com.bushka.cryonomyadapt"
version = "0.0.1"

repositories {
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("https://clojars.org/repo") }
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    implementation("io.reactivex.rxjava2:rxkotlin:2.2.0")
    implementation("com.github.ColeKainz:bittrex-kotlin-client:master-SNAPSHOT")
    api("io.reactivex.rxjava2:rxjava:2.2.0")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}