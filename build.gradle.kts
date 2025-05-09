plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "de.bcxp"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version = "2.3.7"
val logback_version = "1.4.5"



dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Test-Abhängigkeiten
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("de.bcxp.ApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}