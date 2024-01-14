kotlin {
    jvmToolchain(17)
}

plugins {
    //noinspection JavaPluginLanguageLevel
    `java-library`
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    testImplementation(libs.junit.jupiter)
}
