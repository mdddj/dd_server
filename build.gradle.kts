import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sbVersion = "3.3.1"

group = "shop.itbug"
version = "0.1.1"
description = "梁典典的后台服务"


plugins {
    `maven-publish`
    idea
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.springframework.boot") version "3.3.1"
    kotlin("plugin.spring") version "2.0.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.serialization") version "2.0.0"
    id("com.google.osdetector") version "1.7.1"
    kotlin("plugin.allopen") version "2.0.0"
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.maven.apache.org/maven2/") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-validation:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-security:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-cache:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${sbVersion}")
    implementation("org.springframework.boot:spring-boot-starter-websocket:${sbVersion}")
    testCompileOnly("org.springframework.boot:spring-boot-starter-test:${sbVersion}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${sbVersion}")
    compileOnly("org.springframework.boot:spring-boot-devtools:${sbVersion}")
    implementation("io.lettuce:lettuce-core:latest.release")
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
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:latest.release")
    implementation("org.junit.vintage:junit-vintage-engine:5.10.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:latest.release")
    implementation("com.github.oshi:oshi-core:6.6.1")
    implementation(kotlin("reflect"))
    if (osdetector.classifier == "osx-aarch_64") {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.77.Final:${osdetector.classifier}")
    }
}



subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:${sbVersion}")

    }
}




tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}


tasks.withType<KotlinCompile> {
    compilerOptions {
        incremental = true
        jvmTarget = JvmTarget.JVM_21
    }
}



tasks.named<Test>("test") {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

tasks.bootJar {
    archiveFileName.set("dd_service.jar")
}