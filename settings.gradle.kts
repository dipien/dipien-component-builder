plugins {
    id("com.gradle.enterprise").version("3.7.1")
}

include(":dipien-component-builder")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
