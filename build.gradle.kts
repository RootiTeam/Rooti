import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("java-library")
    id("maven-publish")
    id("application")
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
}

group = "dev.ddosnik"
version = "1.0-SNAPSHOT"
description = "Software for Minecraft: Pocket Edition 1.1.x forked Nukkit"

dependencies {
    api(libs.jline)
    api(libs.jopt.simple)
    api(libs.terminalconsoleappender)
    api(libs.jline.reader)
    api(libs.fastutil)
    api(libs.jline.terminal.jna)
    api(libs.jline.terminal)
    api(libs.log4j.api)
    api(libs.log4j.core)
    api(libs.guava)
    api(libs.gson)
    api(libs.snakeyaml)
    api(libs.netty.all)
    api(libs.leveldb)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

application {
    mainClass.set("cn.nukkit.Nukkit")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "rooti"
            version = project.version.toString()

            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    jar {
        archiveClassifier.set("dev")
    }

    shadowJar {
        manifest.attributes["Multi-Release"] = "true"

        transform(Log4j2PluginsCacheFileTransformer())

        destinationDirectory.set(file("$projectDir/target"))
        archiveClassifier.set("")
    }

    javadoc {
        options.encoding = "UTF-8"
    }
}
