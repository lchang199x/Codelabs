plugins {
    `kotlin-dsl`
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

// buildSrc build needs to explicitly add kotlin directory to java srcDirs
sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }

    test {
        java.srcDirs("src/test/kotlin")
    }
}