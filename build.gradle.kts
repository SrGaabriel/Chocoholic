plugins {
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    testApi("junit", "junit", "4.12")

    api(kotlin("stdlib-jdk8"))
    api("junit:junit:4.12")

    api("net.dv8tion:JDA:4.1.1_149")

    api(kotlin("gradle-plugin", "1.3.72"))
    api(kotlin("serialization", "1.3.72"))

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.5")
    api("org.koin:koin-core:2.1.0")
    api("io.github.cdimascio:java-dotenv:5.2.1")
    api("club.minnced:jda-reactor:1.0.0")
    api("com.zaxxer:HikariCP:3.4.2")
    api(group = "mysql", name = "mysql-connector-java", version = "8.0.20")
    api(group = "org.jetbrains.exposed", name = "exposed-jdbc", version = "0.24.1")
    api(group = "org.jetbrains.exposed", name = "exposed-dao", version = "0.24.1")
    api(group = "org.jetbrains.exposed", name = "exposed-core", version = "0.24.1")
    api(group = "org.reflections", name = "reflections", version = "0.9.12")
    api(group = "ch.qos.logback", name = "logback-classic", version = "0.9.26")
    api(group = "org.slf4j", name = "slf4j-api", version = "1.7.29")
    api(group = "com.fasterxml.jackson.datatype", name = "jackson-datatype-jsr310", version = "2.9.8")
    api(group = "com.fasterxml.jackson.dataformat", name = "jackson-dataformat-yaml", version = "2.3.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}