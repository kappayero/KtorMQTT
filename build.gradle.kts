val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "3.0.0"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    // To manage SDK dependencies for your Gradle project, add the Maven bill
    // of materials (BOM) for the AWS SDK for Java 2.x
    implementation(platform("software.amazon.awssdk:bom:2.28.26"))
    // With the bom declared, you specify individual SDK dependencies without a version.
    // Specify the SDK modules your application needs in the dependencies section.
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk.iotdevicesdk:aws-iot-device-sdk:1.21.0")


    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
