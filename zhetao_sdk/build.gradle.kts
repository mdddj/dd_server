plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.2.10"
}

group = "shop.itbug"
version = "0.3.1"

repositories {
    mavenCentral()
}

val ktorVersion = "3.2.3"
dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    implementation("io.ktor:ktor-client-okhttp:${ktorVersion}")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("io.ktor:ktor-client-logging:${ktorVersion}")

    implementation(kotlin("reflect"))
    implementation("io.ktor:ktor-client-okhttp-jvm:3.2.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}