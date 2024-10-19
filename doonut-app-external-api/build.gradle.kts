import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.6"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"

    id("jacoco")
    id("org.sonarqube") version "5.1.0.4882"
}

group = "com.doonutmate"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

sonarqube {
    properties {
        property("sonar.projectKey", "doonutmate123")
        property("sonar.organization", "doonutmate_sonarcloud")
        property("sonar.host.url", "https://sonarcloud.io")
        // sonar additional settings
        property("sonar.sources", "src")
        property("sonar.language", "Kotlin")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.test.inclusions", "**/*Test.java")
        property("sonar.exclusions", "**/test/**, **/Q*.kt, **/*Doc*.kt, **/resources/** ,**/*Application*.kt , **/*Config*.kt, **/*Dto*.kt, **/*Request*.kt, **/*Response*.kt ,**/*Exception*.kt ,**/*ErrorCode*.kt")
        property("sonar.java.coveragePlugin", "jacoco")
    }
}

dependencies {
    implementation(project(":doonut-core"))
    implementation(project(":doonut-support"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }

    // test
    testImplementation("com.ninja-squad:springmockk:3.1.2")
    testImplementation("io.mockk:mockk:1.13.7")
    testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    // database
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    // slack-log-alarm
    implementation("com.github.maricn:logback-slack-appender:1.6.1")

    // json
    implementation("com.googlecode.json-simple:json-simple:1.1")

    // webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // s3
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

    // actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")

    // firebase
    implementation("com.google.firebase:firebase-admin:9.2.0")

    // localstack
    implementation("com.amazonaws:aws-java-sdk-s3")
    implementation("org.testcontainers:localstack")
    testImplementation("org.testcontainers:junit-jupiter")
}

tasks {
    copy {
        from("../doonut-config")
        include("*.yml")
        include("*.p8")
        include("*.json")
        into("src/main/resources")
    }

    copy {
        from("../doonut-config")
        include("application.yml")
        include("application-test.yml")
        into("src/test/resources")
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    withType<Test> {
        enabled = false
    }

    jacocoTestReport {
        dependsOn(test) // tests are required to run before generating the report
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
    test {
        finalizedBy(jacocoTestReport) // report is always generated after tests run
        reports {
            junitXml.required.set(false)
            html.required.set(true)
        }
    }
}
