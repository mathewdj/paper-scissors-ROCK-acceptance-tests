import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
    kotlin("jvm") version "1.8.21"

    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"

    id("io.gitlab.arturbosch.detekt") version "1.20.0-RC2"

    id("org.flywaydb.flyway") version "9.19.0"

    id("com.netflix.dgs.codegen") version "5.12.4"
}

group = "local.mathewdj.paper.scissors.rock"
version = "0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:7.2.0"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.flywaydb:flyway-core")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")

    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.13.0")
    testImplementation("io.cucumber:cucumber-spring:7.13.0")
    testImplementation("io.cucumber:cucumber-java:7.13.0")

    implementation(platform("org.testcontainers:testcontainers-bom:1.17.6"))
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.testcontainers:testcontainers:1.17.6")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")

    testImplementation("io.mockk:mockk:1.13.5")

    testImplementation("io.github.atomfinger:atomfinger-touuid:1.0.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("SKIPPED", "FAILED")
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
    }
}

tasks.withType<GenerateJavaTask> {
    typeMapping = mutableMapOf(
        "UUID" to "java.util.UUID"
    )
}
