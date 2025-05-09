plugins {
    kotlin("jvm") version "2.0.21"
}

group = "de.bcxp"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}