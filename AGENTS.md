# Repository Guide

## Goal

This repository is a modern Minecraft multi-loader template with as much shared code as possible.

Current supported loaders:

- Fabric
- NeoForge

Current Minecraft target:

- 1.21.1

## Structure

- `src/common/`: shared logic, logger usage, command behavior, permission abstractions, tests
- `src/fabric/`: Fabric bootstrap, Fabric command/event registration, Fabric metadata
- `src/neoforge/`: NeoForge bootstrap, NeoForge command/event registration, NeoForge metadata

Keep loader-specific code thin. Prefer putting reusable logic in `src/common/`.

## Fresh Clone Expectations

This project should work on a fresh clone with only:

- Java 21 installed

Do not require contributors to install Gradle globally.
Always use the committed Gradle wrapper.

Primary commands:

```bash
./gradlew test
./gradlew build
./gradlew :fabric:runClient
./gradlew :fabric:runServer
./gradlew :neoforge:runClient
./gradlew :neoforge:runServer
```

If a change introduces a new local prerequisite, document it in `README.md`.

## Code Boundaries

Put in `common`:

- command message text
- validation and parsing logic
- gameplay business rules
- permission node names
- platform-agnostic service interfaces
- unit tests

Put in loader modules only:

- mod entrypoints
- Brigadier/event registration glue
- loader metadata files
- direct loader API usage
- optional integration wiring that differs by platform

## Mappings Note

Fabric currently uses official Mojang mappings in this repo.
NeoForge uses its normal NeoForge/ModDevGradle setup with parchment.

That means Fabric examples from Yarn-based docs may need name translation before use.
Do not switch mappings casually; if changing mappings, verify:

- `./gradlew test`
- `./gradlew build`
- Fabric compile succeeds

## Testing

Prefer unit tests in `src/common/src/test/java/` for shared behavior.
Keep logic testable without requiring a live Minecraft runtime whenever possible.

When adding commands:

- test shared command behavior in `common`
- keep loader modules focused on registration only

## Contributor Guidance

When making changes:

- preserve fresh-clone reproducibility
- avoid hidden local setup steps
- document new workflows in `README.md`
- prefer minimal changes over new abstraction layers
