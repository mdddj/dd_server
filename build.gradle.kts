import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "shop.itbug"
version = "0.4.0"
description = "梁典典的后台服务"

plugins {
    idea
    kotlin("jvm") version "2.3.0-RC"
    kotlin("plugin.spring") version "2.3.0-RC"
    kotlin("plugin.serialization") version "2.3.0-RC"
    kotlin("plugin.allopen") version "2.3.0-RC"
    id("org.springframework.boot") version "4.0.1-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.osdetector") version "1.7.3"
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.maven.apache.org/maven2/") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

val koogVersion = "0.3.0"
val testVersion = "6.1.0-M1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:latest.release")
    implementation("com.alibaba.fastjson2:fastjson2:latest.release")
    implementation("com.alibaba.fastjson2:fastjson2-kotlin:latest.release")
    implementation("com.alibaba.fastjson2:fastjson2-extension-spring6:latest.release")
    implementation("cn.hutool:hutool-all:latest.release")
    implementation("commons-codec:commons-codec:latest.release")
    implementation("com.auth0:java-jwt:latest.release")
    implementation("com.sun.mail:javax.mail:latest.release")
    implementation("io.minio:minio:latest.release")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("mysql:mysql-connector-java:latest.release")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:latest.release")
    implementation("org.junit.vintage:junit-vintage-engine:5.10.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:latest.release")
    implementation("com.github.oshi:oshi-core:latest.release")
    implementation("com.meilisearch.sdk:meilisearch-java:latest.release")
    implementation(kotlin("reflect"))
    if (osdetector.classifier == "osx-aarch_64") {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.77.Final:${osdetector.classifier}")
    }

    //测试
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:${testVersion}")
    testImplementation("org.junit.platform:junit-platform-engine:${testVersion}")

    // ai
    implementation("ai.koog:koog-agents:${koogVersion}")
}




tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    java.sourceCompatibility = JavaVersion.VERSION_25
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
    compilerOptions {
        jvmTarget = JvmTarget.JVM_25
        extraWarnings.set(true)
    }
}

tasks.bootJar {
    archiveFileName.set("dd_service.jar")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}