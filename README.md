# minecraft-mod-template

Modern Minecraft mod template for shared code across Fabric and NeoForge.

## Goals

- support Fabric and NeoForge on one Minecraft version
- keep as much code as possible in `src/common/`
- keep loader-specific modules thin
- work on a fresh clone with only Java 21 installed

## Requirements

- Java 21

Optional helper:

- `.tool-versions` is included for `asdf` users

## Fresh Clone

On a fresh machine, contributors should only need Java 21 and the committed Gradle wrapper.

Basic verification:

```bash
./gradlew test
./gradlew build
```

Gradle will download Minecraft, loader dependencies, mappings, and toolchain artifacts as needed.

The first NeoForge setup can take a while because it prepares patched Minecraft artifacts.

## Development Runs

Fabric:

```bash
./gradlew :fabric:runClient
./gradlew :fabric:runServer
```

NeoForge:

```bash
./gradlew :neoforge:runClient
./gradlew :neoforge:runServer
```

NeoForge dedicated server note:

- accept the Minecraft EULA in the generated run directory
- set `online-mode=false` in `server.properties` if you want to join with the dev player

## Project Structure

- `src/common/`: shared logic, command definitions, permission abstractions, tests
- `src/fabric/`: Fabric bootstrap, Fabric command registration, Fabric metadata
- `src/neoforge/`: NeoForge bootstrap, NeoForge command registration, NeoForge metadata

## Template Rules

- put business logic in `common`
- put Brigadier registration glue in loader modules
- prefer plain Java in `common`
- prefer unit tests in `src/common/src/test/java/`
- document any new local prerequisite in this README

## Mappings

- Fabric currently uses official Mojang mappings in this repo
- NeoForge uses parchment on top of NeoForge's toolchain

Because of that, Fabric examples from Yarn-based docs may need class-name translation before use.

## Current Example

The template currently includes cross-loader example commands:

- sends `Hello World!` to chat
- logs the response through the shared logger
- keeps command behavior in `common`
- keeps registration glue in loader modules

Additional sample commands:

- `/foo`
- `/bar`

## Build Outputs

After `./gradlew build`, loader jars are produced in:

- `src/fabric/build/libs/`
- `src/neoforge/build/libs/`

## Contributing

Before opening changes, run:

```bash
./gradlew test
./gradlew build
```

Repository-specific guidance for coding agents and contributors lives in `AGENTS.md`.
