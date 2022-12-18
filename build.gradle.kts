import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.allopen") version "1.7.20"
    id("io.quarkus")
    id("com.google.devtools.ksp") version "1.7.20-1.0.7"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

val arrowVersion = "1.1.3"
val mutektVersion = "1.0.0-alpha03"
val kotestVersion = "5.5.4"

dependencies {
    implementation("io.quarkus:quarkus-config-yaml")
//    implementation("io.quarkus:quarkus-grpc")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")

    // zeromq
    implementation("org.zeromq:jeromq:0.5.2")

//    implementation("io.helins:linux-i2c:1.0.2")

    // arrow
    implementation(platform("io.arrow-kt:arrow-stack:$arrowVersion"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-fx-coroutines")
    implementation("io.arrow-kt:arrow-optics")

    // logging
    implementation("io.github.microutils:kotlin-logging:2.1.21")
    implementation("net.logstash.logback:logstash-logback-encoder:7.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("io.quarkiverse.mockk:quarkus-junit5-mockk:1.1.1")

    // kotest
    testImplementation("io.kotest:kotest-assertions-core:${kotestVersion}")
    testImplementation("io.kotest:kotest-runner-junit5:${kotestVersion}")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.2.5")

    testImplementation("org.jeasy:easy-random-core:5.0.0")
    testImplementation("com.tngtech.archunit:archunit-junit5:0.23.0")
}

group = "rg"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")

    useJUnitPlatform {

    }

    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
    reports {
        html.required.set(true)
        junitXml.required.set(true)
    }
}
allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}
