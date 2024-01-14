@file:Suppress("UnstableApiUsage")

// PluginManagementSpec: Configures how plugins are resolved. @since 3.5
pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
// DependencyResolutionManagement: Allows configuring dependency resolution for all projects of the build. @since 6.8
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/google")
        google()
        mavenCentral()
    }
}

rootProject.name = "Codelabs"
include(":app")
include(":jk")
include(":lc")
