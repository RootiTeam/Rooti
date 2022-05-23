plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
}

group = "dev.ddosnik"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("jline:jline:2.14.6")
    implementation("net.sf.jopt-simple:jopt-simple:5.0.4")
    implementation("net.minecrell:terminalconsoleappender:1.1.1")
    implementation("org.jline:jline-reader:3.21.0")
    implementation("it.unimi.dsi:fastutil:6.3")
    implementation("org.jline:jline-terminal-jna:3.21.0")
    implementation("org.jline:jline-terminal:3.21.0")
    implementation("org.apache.logging.log4j:log4j-api:2.17.2")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.yaml:snakeyaml:1.30")
    implementation("io.netty:netty-all:4.1.76.Final")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation(files("libs/leveldb.jar"))

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))

    withSourcesJar()
    // withJavadocJar() // @ddosnikgit cleanun javadoc's please
}

tasks {
    build { dependsOn(shadowJar) }
    shadowJar {
        archiveFileName.set("Rooti.jar")
        transform(com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer::class.java)

        manifest {
            attributes["Main-Class"] = "cn.nukkit.Nukkit"
            attributes["Multi-Release"] = "true"
        }
    }
    withType<JavaCompile> { options.encoding = "UTF-8" }
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
