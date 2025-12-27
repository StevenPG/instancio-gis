# instancio-gis
An instancio extension that will provide auto-registered custom generators for a popular gis libraries.

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=StevenPG_instancio-gis&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=StevenPG_instancio-gis)
[![javadoc](https://javadoc.io/badge2/com.stevenpg.instancio/locationtech-core/javadoc.svg)](https://javadoc.io/doc/com.stevenpg.instancio/locationtech-core)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=StevenPG_instancio-gis&metric=coverage)](https://sonarcloud.io/summary/new_code?id=StevenPG_instancio-gis)

### Tasks

Tasks for v1.0.0:

- maven central release with first version
- upload with sources
- Add javadoc
- deploy to maven central (set up plugin)
- deploy to javadoc (deploy javadoc.jar) (https://javadoc.io/)
- Sample project shouldn't get published
- each sub project should get published to maven central
- add badges (maven central, javadoc, sonarcloud)
- mirror justfile: https://github.com/instancio/instancio/blob/main/justfile
- Add script to validate existence of files based on implemented types


## What is this Repository for?

This project is intended to be an extension for [instancio](https://github.com/instancio/instancio).

The modules here are available on Maven Central for download and utilize the
[instancio custom generators](https://www.instancio.org/user-guide/#custom-generators) feature to
generate random values that are not supported by the main instancio-core library.

This is achieved using the [Instancio Service Provider Interface](https://www.instancio.org/user-guide/#instancio-service-provider-interface).

## Supported Libraries

Currently, the following libraries are supported:

- [LocationTech JTS](https://locationtech.org/jts/)
- PostGIS-Java

## How To

You can use the extension by adding the following dependencies to your project:

## Usage

You'll need to include the actual dependency for the library you want to use. Once that's
included, you can use the generators provided by the library in your Instancio invocations.

[Maven Central Home](https://central.sonatype.com/search?q=com.stevenpg.instancio)

### LocationTech JTS 

#### LocationTech JTS - Core

Root Dependency

```gradle
implementation("org.locationtech.jts:jts-core:${version}")
```

Maven - pom.xml

```xml
<dependency>
    <groupId>com.stevenpg.instancio</groupId>
    <artifactId>locationtech-core</artifactId>
    <version>${version}</version>
</dependency>
```

Gradle - build.gradle

```gradle
dependencies {
  implementation "com.stevenpg.instancio:locationtech-core:${version}"
}
```

Kotlin - build.gradle.kts

```kotlin
  implementation("com.stevenpg.instancio:locationtech-core:${version}")
```

### PostGIS-Java

#### PostGIS-Java - JDBC

Maven - pom.xml

```xml
<dependency>
    <groupId>com.stevenpg.instancio</groupId>
    <artifactId>postgis-jdbc</artifactId>
    <version>${version}</version>
    <scope>test</scope>
</dependency>
```

Gradle - build.gradle

```gradle
dependencies {
  testImplementation "com.stevenpg.instancio:postgis-jdbc:${version}"
}
```

Kotlin - build.gradle.kts

```kotlin
  testImplementation("com.stevenpg.instancio:postgis-jdbc:${version}")
```

## Contributing

This project aims to provide comprehensive GIS library support for Instancio, and community contributions can help make that possible.

### Getting Started

#### Prerequisites

- Java 17
- Gradle 8.14

#### Development Setup

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/instancio-gis.git
   cd instancio-gis
   ```
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run tests to ensure everything works:
   ```bash
   ./gradlew test
   ```

### Contributing Guidelines

#### Before You Start

- Check existing issues to see if your idea is already being discussed
- For new features, please create an issue first to discuss the approach
- For bug fixes, you can submit a PR directly with a clear description

#### Adding New Library Support

When adding support for a new GIS library:

1. Create a new module under the appropriate directory structure
2. Implement custom generators following Instancio's SPI pattern
3. Add comprehensive tests with good coverage
4. Update documentation including:
   - README.md usage examples
   - Maven/Gradle dependency snippets
   - Supported library versions

#### Code Standards

- Follow existing code style and conventions
- Write meaningful commit messages
- Include tests for new functionality
- Ensure all tests pass before submitting
- Add JavaDoc comments for public APIs

#### Testing

- All new generators must have unit tests
- Aim for high test coverage
- Test edge cases and error conditions
- Use meaningful test names that describe the scenario

### Questions or Need Help?

- Create an issue for questions about contributing
- Check the [Instancio documentation](https://www.instancio.org/user-guide/) for SPI implementation details
- Review existing modules for implementation patterns

Thank you for contributing to instancio-gis!

## Instancio

Here are the official links:

- [Website](https://www.instancio.org/)
- [GitHub](https://github.com/instancio/instancio)
- [Documentation](https://www.instancio.org/user-guide/)
- [JavaDoc](https://javadoc.io/doc/org.instancio/instancio-core/latest/org/instancio/Instancio.html)
