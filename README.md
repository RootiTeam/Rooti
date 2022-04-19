# Rooti
Software for Minecraft Bedrock Edition 1.1.x forked Nukkit.

Installation Java8
-------------
- `sudo apt-get update && sudo apt-get install default-jdk`
- `sudo apt-get install oracle-java8-installer`
- Checking java version: `java -version`

Build JAR file
-------------
- `git submodule update --init`
- `apt install -y maven`
- `cd lib && mvn install:install-file -Dfile=leveldb.jar -DgroupId=com.tinfoiled.mcpe.leveldb -DartifactId=leveldb -Dversion=0.8 -Dpackaging=jar && cd ..`
- `mvn clean && mvn package`

Running
-------------
- `cd target && export TERM=xterm-color`
- `java -jar nukkit-1.0-SNAPSHOT.jar`
