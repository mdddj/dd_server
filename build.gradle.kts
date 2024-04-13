import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val sbVersion = "3.2.4"


plugins {
    `maven-publish`
    idea
    id("org.jetbrains.kotlin.jvm") version "2.0.0-RC1"
    id("org.springframework.boot") version "3.2.4"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
    kotlin("plugin.spring") version "2.0.0-RC1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.serialization") version "2.0.0-RC1"
    id("com.google.osdetector") version "1.7.1"
}



repositories {
    mavenLocal()
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
    implementation("io.lettuce:lettuce-core:latest.release")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0-Beta2")
    implementation("com.alibaba.fastjson2:fastjson2:latest.release")
    implementation("com.alibaba.fastjson2:fastjson2-kotlin:latest.release")
    implementation("com.alibaba.fastjson2:fastjson2-extension-spring6:latest.release")
    implementation("cn.hutool:hutool-all:latest.release")
    implementation("org.bouncycastle:bcprov-jdk15on:latest.release")
    implementation("commons-codec:commons-codec:latest.release")
    implementation("com.auth0:java-jwt:latest.release")
    implementation("com.sun.mail:javax.mail:latest.release")
    implementation("io.minio:minio:latest.release")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.springframework.boot:spring-boot-devtools:${sbVersion}")
    runtimeOnly("mysql:mysql-connector-java:latest.release")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:latest.release")
    testCompileOnly("org.springframework.boot:spring-boot-starter-test:${sbVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:latest.release")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${sbVersion}")
    implementation("org.junit.vintage:junit-vintage-engine:5.10.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:latest.release")
    implementation(kotlin("reflect"))
    if (osdetector.classifier == "osx-aarch_64") {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.77.Final:${osdetector.classifier}")
    }
}

group = "shop.itbug"
version = "2.0.0"
description = "梁典典的后台服务"



tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}


tasks.withType<KotlinCompile> {
    compilerOptions {
        incremental = true
    }
}


tasks.named<Test>("test") {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}
