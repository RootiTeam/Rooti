# Rooti
[![Java CI](https://github.com/RootiTeam/Rooti/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/RootiTeam/Rooti/actions/workflows/gradle.yml)
Software for Minecraft Pocket Edition 1.1.x forked Nukkit.

Java 8 installation
-------------
- `sudo apt-get update && sudo apt-get install default-jdk`
- `sudo apt-get install openjdk-8-jdk`
- `update-alternatives --config java`, choose /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
- Check java version: `java -version`

Building
-------------
- `./gradlew build --no-daemon`
- Jar file (Rooti.jar) is located in build/libs

Running
-------------
- `java -Xmx1G -jar Rooti.jar`
