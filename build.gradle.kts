plugins {
	`kotlin-dsl`
	// https://docs.gradle.org/current/userguide/javaGradle_plugin.html
	id("java-gradle-plugin")
	id("com.jdroid.component.builder")
	id("com.jdroid.gradle.plugin")
}

buildscript {
//	repositories {
//		jcenter()
//		gradlePluginPortal()
//		String localMavenRepoEnabled = project.hasProperty("LOCAL_MAVEN_REPO_ENABLED") ? project.ext.get("LOCAL_MAVEN_REPO_ENABLED") : System.getenv("LOCAL_MAVEN_REPO_EMABLED")
//		boolean isLocalMavenRepoEnabled = localMavenRepoEnabled != null && localMavenRepoEnabled == "true"
//		if (isLocalMavenRepoEnabled) {
//			String localMavenRepo = project.hasProperty("LOCAL_MAVEN_REPO") ? project.ext.get("LOCAL_MAVEN_REPO") : System.getenv("LOCAL_MAVEN_REPO")
//			if (localMavenRepo != null) {
//				maven { url = localMavenRepo }
//			}
//		}
//		maven { url = "https://oss.sonatype.org/content/repositories/snapshots/" }
//		mavenCentral()
//	}
	dependencies {

		// https://github.com/maxirosson/jdroid-component-builder/blob/master/CHANGELOG.md
		classpath("com.jdroidtools:jdroid-component-builder:1.1.0")

		// https://github.com/maxirosson/jdroid-gradle-plugin/blob/master/CHANGELOG.md
		classpath("com.jdroidtools:jdroid-gradle-project-plugin:1.1.0")
	}
}

version = "2.0.0"

//ext.JDROID_GITHUB_REPOSITORY_NAME = "jdroid-component-builder"
//
//ext.PROJECT_NAME = "Jdroid Component Builder Plugin"



// https://docs.gradle.org/current/userguide/javaGradle_plugin.html
//apply(plugin = "java-gradle-plugin")
//
//apply(plugin = "com.jdroid.component.builder")
//apply(plugin = "com.jdroid.gradle.plugin")

description = "Gradle Plugin to build Jdroid Components"

repositories {
	jcenter()
	gradlePluginPortal()
//	String localMavenRepoEnabled = project.hasProperty("LOCAL_MAVEN_REPO_ENABLED") ? project.ext.get("LOCAL_MAVEN_REPO_ENABLED") : System.getenv("LOCAL_MAVEN_REPO_EMABLED")
//	boolean isLocalMavenRepoEnabled = localMavenRepoEnabled != null && localMavenRepoEnabled == "true"
//	if (isLocalMavenRepoEnabled) {
//		String localMavenRepo = project.hasProperty("LOCAL_MAVEN_REPO") ? project.ext.get("LOCAL_MAVEN_REPO") : System.getenv("LOCAL_MAVEN_REPO")
//		if (localMavenRepo != null) {
//			maven(url = localMavenRepo)
//		}
//	}
	maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
	mavenCentral()
}

dependencies {

	// https://github.com/maxirosson/jdroid-java/blob/master/CHANGELOG.md
	implementation("com.jdroidtools:jdroid-java-core:2.0.0")

	// https://github.com/maxirosson/jdroid-java-github/releases
	implementation("com.jdroidtools:jdroid-java-github:1.0.0")

	// https://plugins.gradle.org/plugin/com.gradle.build-scan
	implementation("com.gradle:build-scan-plugin:2.1")

	testImplementation("junit:junit:4.12")
}

gradlePlugin {
	plugins {
		register("componentBuilderPlugin") {
			id = "com.jdroid.component.builder"
			implementationClass = "com.jdroid.component.builder.ComponentBuilderGradlePlugin"
		}
	}
}