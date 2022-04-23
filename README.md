# Rooti
Software for Minecraft Bedrock Edition 1.1.x forked Nukkit.

Installation Java8
-------------
- `sudo apt-get update && sudo apt-get install default-jdk`
- `sudo apt-get install openjdk-8-jdk`
- `update-alternatives --config java`, choose /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
- Checking java version: `java -version`

Build JAR file
-------------
- `git submodule update --init`
- `apt install -y maven`
- `mvn install:install-file -Dfile=leveldb.jar -DgroupId=com.tinfoiled.mcpe.leveldb -DartifactId=leveldb -Dversion=0.8 -Dpackaging=jar`
- `mvn clean && mvn package`

Running
-------------
- `cd target && export TERM=xterm-color`
- `java -Xmx1G -Xms1G -jar rooti-1.0.1.jar`
