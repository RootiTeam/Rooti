# Rooti
[![Java CI](https://github.com/RootiTeam/Rooti/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/RootiTeam/Rooti/actions/workflows/gradle.yml)
<a href="https://github.com/RootiTeam/Rooti/releases/latest"><img alt="Latest release" src="https://img.shields.io/github/v/release/RootiTeam/Rooti?label=release&sort=semver"></a>

Software for Minecraft Pocket Edition 1.1.x forked Nukkit.

Java 19 installation
-------------
- `sudo apt-get update && sudo apt-get upgrade`
- `wget https://download.oracle.com/java/19/latest/jdk-19_linux-x64_bin.deb`
- `sudo apt-get -qqy install ./jdk-19_linux-x64_bin.deb`
- `sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-19/bin/java 1919`
- Check java version: `java -version`

Building
-------------
- `./gradlew build --no-daemon`
- Jar file (rooti-1.0-SNAPSHOT.jar) is located in target

Running
-------------
- `java -Xmx1G -jar rooti-1.0-SNAPSHOT.jar`
