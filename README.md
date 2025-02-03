# EcoSnap

A **community-driven** application designed to identify, report, and clean up polluted or
disaster-affected areas. **EcoSnap** empowers individuals worldwide to actively participate in
environmental protection by reporting polluted locations and earning rewards for their
contributions.

The app fosters a **collaborative approach**, turning every user into a changemaker. By enabling
citizens, NGOs, and local authorities to coordinate effectively, **EcoSnap** transforms waste
reporting into a community-driven initiative for a cleaner planet.

This app is particularly valuable in regions facing severe urban pollution issues, where waste
management is often inadequate.  
Countries such as **India, Pakistan, Indonesia, Egypt**, and other **Southeast Asian nations**
suffer from overflowing streets and inefficient waste collection. **EcoSnap** provides local
populations with a tool to self-organize, collaborate with authorities and NGOs, and improve their
living environment.

By promoting **citizen engagement and collective responsibility**, **EcoSnap** transforms
environmental cleanup efforts into a positive, rewarding, and community-driven movement. 🌍♻️

## Business Plan

- [Available on Notion](https://justjerem.notion.site/Ecosnap-Capture-Act-for-a-Greener-Planet-18f2da74de5380bab35cf1ac1c74f0de?pvs=4) -
  Also French version
  available [here](https://justjerem.notion.site/Ecosnap-Capture-Agis-pour-une-Plan-te-Plus-Verte-18e2da74de53808aad83c92e32400552)

## Features

- **Report Polluted Areas**  
  Users can capture one or more photos of polluted locations and submit a report directly through
  the app.

- **Community Participation**  
  Users can add additional photos and tag other participants, earning more points and increasing
  their impact within the community.

- **Interactive Map - Next Update**  
  Access a real-time map showcasing all reported polluted areas. A future update will allow users to
  see their friends’ contributions as well.

- **Cleanup Validation**  
  Upon selecting a reported location, users are prompted to upload an “after” photo of the cleaned
  site and tag participants who contributed.

- **Points and Rewards System**  
  Each action earns users points, which are credited to their profile once validated by the
  appropriate authorities.

- **Collaboration with NGOs and Local Authorities**  
  EcoSnap helps citizens connect with NGOs and government agencies to organize cleanups and improve
  waste management efficiency.

### Additional Highlights

- **Dark & Light Mode**  
  Supports both dark and light themes for an optimal user experience across various lighting
  conditions.

## Technical Highlights

- Written entirely in **Kotlin**.
- Built with **Jetpack Compose** for a declarative and modern UI.
- Integrates **Koin** for dependency injection, ensuring modularity and easy testability.
- Utilizes **Android Jetpack** components, including:
  - **ViewModel** for state management.
  - **StateFlow** for reactive data handling.
  - **Navigation Component** for seamless navigation between screens.
- Implements **Clean Architecture** and the **MVI pattern** for a clear separation of concerns and
  maintainability.
- Designed as a **multi-module** application, allowing individual modules—particularly the camera
  module—to be reused in other applications.
- Uses a **build logic** approach, centralizing all Gradle configurations in a single place, making
  the project easier to maintain and scale.
- Follows an **offline-first** approach with **Room Database** and **DataStore**, allowing smooth
  operation even without an Internet connection.

## Video

## Screen Shots

[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/0.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/1.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/1.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/1.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/2.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/2.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/4.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/4.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/5.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/4.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/6.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/4.png)
[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/7.png" width=200 />](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/4.png)

## Architecture Overview

[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/archi.png"/>](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/Overview.png)

## Modules Overview

[<img src="https://raw.githubusercontent.com/JustJerem/EcoSnap/master/documentation/module.png"/>](https://raw.githubusercontent.com/JustJerem/IdentityReader/master/documentation/Overview.png)

## 🛠 Built With

### Core Libraries

- [AndroidX Core KTX](https://developer.android.com/kotlin/ktx) - Extensions for Android core
  libraries to write concise and idiomatic Kotlin code.
- [Lifecycle Runtime KTX](https://developer.android.com/topic/libraries/architecture/lifecycle) -
  Kotlin extensions for Android lifecycle-aware components.
- [Koin](https://insert-koin.io/) - Dependency injection framework for Kotlin.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Library for
  asynchronous programming in Kotlin.

### User Interface

- [Compose BOM](https://developer.android.com/jetpack/compose/bom) - Manages consistent versions of
  Jetpack Compose dependencies.
- [Compose UI](https://developer.android.com/jetpack/compose/ui) - Toolkit for building declarative
  UI in Android.
  - [UI Graphics](https://developer.android.com/jetpack/compose/ui) - Library for managing Compose
    graphics.
  - [UI Tooling](https://developer.android.com/jetpack/compose/tooling) - Tools for UI previews and
    debugging.
  - [UI Tooling Preview](https://developer.android.com/jetpack/compose/tooling) - Preview and
    inspect UI directly in the IDE.
- [Material Icons Extended](https://developer.android.com/reference/androidx/compose/material/icons/package-summary) -
  A collection of extended Material Design icons for Compose applications.
- [Material 3](https://m3.material.io/) - Modern Material Design components for expressive UIs.

### Navigation

- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Simplifies and
  type-safe navigation in Compose.

### Camera & Media

- [CameraX](https://developer.android.com/training/camerax) - Camera library for simplified camera
  app development.
  - [Camera View](https://developer.android.com/training/camerax) - Provides a simple API for camera
    previews.
  - [Camera Core](https://developer.android.com/training/camerax) - Core functionality for camera
    features.
  - [Camera2](https://developer.android.com/training/camerax) - Backward compatibility with Camera2
    API.
  - [Camera Lifecycle](https://developer.android.com/training/camerax) - Lifecycle-aware components
    for cameras.
  - [Camera Extensions](https://developer.android.com/training/camerax) - Enhances camera
    capabilities with effects.

### Networking

- [Ktor](https://ktor.io/) - Asynchronous client and server framework for Kotlin.
  - [Ktor Client Auth](https://ktor.io/docs/auth.html) - Authentication for HTTP clients.
  - [Ktor Client CIO](https://ktor.io/docs/http-client-engines.html#cio) - HTTP client engine for
    Ktor.
  - [Ktor Client Content Negotiation](https://ktor.io/docs/serialization.html) - Serialization
    support for HTTP clients.
  - [Ktor Client Core](https://ktor.io/docs/http-client.html) - Core functionality for HTTP clients.
  - [Ktor Client Logging](https://ktor.io/docs/client-logging.html) - Logging support for HTTP
    clients.
  - [Ktor Serialization Kotlinx JSON](https://ktor.io/docs/serialization.html#kotlinx-json) - JSON
    serialization for Ktor.

### Storage & Database

- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Database persistence
  library for Android.
  - [Room KTX](https://developer.android.com/jetpack/androidx/releases/room) - Kotlin extensions for
    Room.
  - [Room Compiler](https://developer.android.com/jetpack/androidx/releases/room) - Annotation
    processor for Room.
  - [Room Gradle Plugin](https://developer.android.com/jetpack/androidx/releases/room) - Plugin for
    generating Room components.

### Testing

- [JUnit](https://junit.org/junit5/) - Framework for unit testing in Java.
- [AndroidX JUnit](https://developer.android.com/testing) - Extensions for JUnit to test Android
  components.
- [Espresso Core](https://developer.android.com/training/testing/espresso) - A framework for Android
  UI testing.
- [Espresso Intents](https://developer.android.com/training/testing/espresso) - Testing framework
  for Android UI, specialized for intent validation.
- [UI Test Manifest](https://developer.android.com/jetpack/compose/testing) - Simplifies testing
  Compose UI components.
- [UI Test JUnit4](https://developer.android.com/jetpack/compose/testing) - Compose UI testing with
  JUnit4.

### Serialization

- [Kotlin Serialization JSON](https://github.com/Kotlin/kotlinx.serialization) - Library for JSON
  serialization in Kotlin.

### Security & Cryptography

- [AndroidX AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) - Backward
  compatibility library for Android components.

### Build System & Gradle Plugins

- [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) - Official
  Gradle plugin for Android projects.
- [Kotlin Gradle Plugin](https://kotlinlang.org/docs/gradle.html) - Plugin for Kotlin-based
  projects.
- [KSP (Kotlin Symbol Processing)](https://kotlinlang.org/docs/ksp-overview.html) - Annotation
  processor for Kotlin.
- [Room Gradle Plugin](https://developer.android.com/jetpack/androidx/releases/room) - Plugin for
  generating Room components.