plugins {
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

tasks.register<JavaExec>("contextReceivers") {
    dependsOn("classes")
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("adhocpoly.ContextReceiversKt")
}