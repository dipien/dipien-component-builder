plugins {
    id("com.gradle.enterprise").version("3.7.2")
}

include(":dipien-component-builder")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
