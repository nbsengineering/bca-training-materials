# MyABC Project

This is the official repository for the MyABC Android application. It's a modern, multi-module Android app built entirely with Kotlin and Jetpack Compose.

## 📚 Codelab & Documentation

For a complete guide, project walkthrough, and technical documentation, please refer to the official Codelab document:

[**MyABC Codelab (Google Docs)**](https://docs.google.com/document/d/1DMyLKHznm_XSD_bn3NPeAAlwYht6SkQRQC1b2rXEkd4/edit?usp=sharing)

## ✨ Features

Based on the project structure, the app includes the following features:

* **Splash Screen:** An initial loading screen for the application.
* **Authentication:** A dedicated feature module for user login.
* **Dashboard:** The main user dashboard screen after authentication.
* **Bottom Sheet Navigation:** Implements complex navigation flows using a modal bottom sheet container.
* **Deeplinking:** Core support for handling app deeplinks.

## 🛠️ Technology Stack & Architecture

This project leverages a modern Android tech stack:

* **Language:** 100% [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for a fully declarative UI.
* **Navigation:** [Jetpack Navigation Component](https://developer.android.com/jetpack/compose/navigation) for managing app navigation flow.
* **Architecture:** Follows a multi-module (feature-based) architecture.

### Module Structure

The project is split into several modules:

* `:app`: The main application module. It integrates all other modules and contains the main activity and navigation graph.
* `:authentication`: A feature module dedicated to user login and auth flows.
* `:core`: A shared module containing base utilities, theme definitions, shared components (like `HeaderBottomSheet`), and navigation extensions.

## 🚀 Getting Started

1.  Clone the repository:
    ```bash
    git clone [https://github.com/fachridantm/myabc.git](https://github.com/fachridantm/myabc.git)
    ```
2.  Open the project in Android Studio (latest stable version recommended).
3.  Let Gradle sync all dependencies specified in the TOML file and build scripts.
4.  Build and run the `:app` configuration on an emulator or physical device.