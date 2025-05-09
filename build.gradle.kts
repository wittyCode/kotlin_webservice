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
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")

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

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.register("dockerBuild") {
    group = "docker"
    description = "Erstellt ein Docker-Image für die Anwendung."

    doLast {
        val imageName = "kotlin_webservice:latest"
        exec {
            commandLine("podman", "build", "-t", imageName, ".")
        }
    }
}

kotlin {
    jvmToolchain(22)
}