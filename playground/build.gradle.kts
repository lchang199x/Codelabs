kotlin {
    jvmToolchain(17)
}

plugins {
    //noinspection JavaPluginLanguageLevel
    `java-library`
    kotlin("jvm")
}

sourceSets {
    main {
        java {
            java.srcDirs("src/main/interview")
        }
    }
}

tasks.withType<Test>() {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit.jupiter) // api&engine all in jupiter
}
