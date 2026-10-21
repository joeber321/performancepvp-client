# PerformancePvP Client

A clean, legitimate Fabric performance and utility client starter for Linux Mint.

## Requirements
- Java 21
- Gradle
- Fabric Loader for Minecraft 1.21.1

## Install dependencies
```bash
sudo apt update
sudo apt install openjdk-21-jdk gradle git
```

## Build
```bash
./gradlew build
```

## Run in dev
```bash
./gradlew runClient
```

## Install the mod
Copy the JAR from `build/libs/` into:

```bash
~/.minecraft/mods
```

## Mint launcher
You can also use the included launcher files:

```bash
chmod +x build-local.sh run-local.sh
./build-local.sh
./run-local.sh
```

This project is a legitimate performance and utility mod starter, not a cheat client.