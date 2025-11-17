# Compose Customization Training Project

This repository contains the sample project used for the **“Composable Customization: Building Custom Layouts & Adaptive UI in Jetpack Compose”** training session.
It demonstrates a scalable UI system using Atomic Design, focusing on how to assemble atoms → molecules → organisms → screens using Jetpack Compose.

## 📚 Codelab & Documentation

The complete training material, including slides, tasks, and walkthroughs, is available in the official Google Docs codelab:

[👉 Compose Customization Codelab](https://docs.google.com/document/d/1gVleIhTDUaLnnONCYc-zHoKH2w-YtMMv7Gyv3g1k0ow)

This codelab will guide you step-by-step as you build custom layouts and adaptive UI components using composable reusability principles.

## ✨ What This Project Demonstrates

This project is designed for hands-on practice with Jetpack Compose UI architecture. It includes:

### Atomic Design Structure

- **Atoms**: Core UI primitives (Avatar, SurfaceIcon, etc.)
- **Molecules**: Small UI combinations (ResourceItem, Chip, SummaryCard, etc.)
- **Organisms**: Complex UI sections (ProfileComponent, MenuGrid, TransactionHistorySection, etc.)
- **Screens**: Full pages composed of multiple organisms

### UI Customization

- Component theming
- Reusable layout patterns
- Adaptive UI using custom sections and flexible arrangements

### Real-world Patterns

- LazyGrid, Flexbox, and custom sections
- Parameter-driven UI customization
- Separation of UI layers for scalability

## 🛠️ Technology Stack

This project uses a modern Android UI stack:
- **Kotlin** — primary language
- **Jetpack Compose** — declarative and scalable UI
- **Material 3** — design system support
- **AndroidX Compose Navigation** — single-activity navigation architecture

## 📂 UI Module Structure

```
ui/
 ├── atoms/
 ├── molecules/
 ├── organisms/
 └── screens/
 ```

This structure is intentionally clean and predictable so that developers can easily compose, customize, and extend UI components.

## 🚀 Getting Started
**1. Clone the repository**

`https://code.nbs.dev/nbsengineering/bca-compose-materials/-/tree/codelab-start/Day%202/compose-customization`

**2. Open in Android Studio**

Use the latest stable version for the best Compose support.

**3. Let Gradle Sync**

The TOML version catalog handles dependencies.

**4. Run the project**

Select the `app` configuration and run it on an emulator or device.

## 🎯 Who This Project Is For

- Android Developers learning composable architecture
- Teams adopting Atomic Design in Compose
- Anyone needing clean examples of reusable UI components