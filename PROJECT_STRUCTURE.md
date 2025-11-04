# Project Structure

## 📁 Complete File Tree

```
invoice-generator-business-management-flutter/
│
├── 📄 README.md                          # Main project documentation
├── 📄 QUICK_START.md                     # Quick start guide for developers
├── 📄 DESIGN_GUIDE.md                    # Visual design specifications
├── 📄 PROJECT_STRUCTURE.md               # This file
├── 📄 .gitignore                         # Git ignore rules
│
├── 📄 build.gradle.kts                   # Root build configuration
├── 📄 settings.gradle.kts                # Gradle settings
├── 📄 gradle.properties                  # Gradle properties
│
├── 📁 gradle/
│   └── 📁 wrapper/
│       └── 📄 gradle-wrapper.properties  # Gradle wrapper configuration
│
└── 📁 app/
    ├── 📄 build.gradle.kts               # App module build configuration
    ├── 📄 proguard-rules.pro             # ProGuard rules for release builds
    │
    └── 📁 src/
        └── 📁 main/
            ├── 📄 AndroidManifest.xml    # App manifest
            │
            ├── 📁 java/com/invoicegenerator/
            │   │
            │   ├── 📄 MainActivity.kt    # Main activity (app entry point)
            │   │
            │   ├── 📁 ui/
            │   │   ├── 📁 screens/
            │   │   │   ├── 📄 LoginScreen.kt       # Login screen UI
            │   │   │   └── 📄 DashboardScreen.kt   # Dashboard screen UI
            │   │   │
            │   │   ├── 📁 components/
            │   │   │   ├── 📄 GradientButton.kt    # Reusable gradient button
            │   │   │   ├── 📄 CustomTextField.kt   # Reusable text field
            │   │   │   └── 📄 FeatureCard.kt       # Reusable feature card
            │   │   │
            │   │   ├── 📁 theme/
            │   │   │   ├── 📄 Color.kt             # Color definitions
            │   │   │   ├── 📄 Type.kt              # Typography definitions
            │   │   │   └── 📄 Theme.kt             # Material 3 theme
            │   │   │
            │   │   └── 📁 navigation/
            │   │       └── 📄 NavGraph.kt          # Navigation graph
            │   │
            │   ├── 📁 data/                        # (Empty - for future use)
            │   │
            │   └── 📁 utils/
            │       └── 📄 ValidationUtils.kt       # Input validation utilities
            │
            └── 📁 res/
                ├── 📁 values/
                │   ├── 📄 strings.xml              # String resources
                │   ├── 📄 colors.xml               # Color resources
                │   └── 📄 themes.xml               # XML theme definitions
                │
                ├── 📁 xml/
                │   ├── 📄 backup_rules.xml         # Backup rules
                │   └── 📄 data_extraction_rules.xml # Data extraction rules
                │
                ├── 📁 drawable/                    # (Empty - for images/icons)
                ├── 📁 mipmap-hdpi/                 # (Empty - for launcher icons)
                ├── 📁 mipmap-mdpi/                 # (Empty - for launcher icons)
                ├── 📁 mipmap-xhdpi/                # (Empty - for launcher icons)
                ├── 📁 mipmap-xxhdpi/               # (Empty - for launcher icons)
                └── 📁 mipmap-xxxhdpi/              # (Empty - for launcher icons)
```

## 📊 File Statistics

### Kotlin Files (11 files)
- **Screens**: 2 files (LoginScreen.kt, DashboardScreen.kt)
- **Components**: 3 files (GradientButton.kt, CustomTextField.kt, FeatureCard.kt)
- **Theme**: 3 files (Color.kt, Type.kt, Theme.kt)
- **Navigation**: 1 file (NavGraph.kt)
- **Utils**: 1 file (ValidationUtils.kt)
- **Main**: 1 file (MainActivity.kt)

### XML Files (6 files)
- **Manifest**: 1 file
- **Resources**: 3 files (strings.xml, colors.xml, themes.xml)
- **Configuration**: 2 files (backup_rules.xml, data_extraction_rules.xml)

### Build Files (5 files)
- **Gradle**: 4 files (.kts files)
- **Properties**: 1 file (gradle.properties)

### Documentation (4 files)
- README.md
- QUICK_START.md
- DESIGN_GUIDE.md
- PROJECT_STRUCTURE.md

## 🎯 Key Components Breakdown

### 1. Entry Point
```
MainActivity.kt
    └── Sets up Compose theme
        └── Initializes AppNavGraph
```

### 2. Navigation Flow
```
AppNavGraph (NavGraph.kt)
    ├── Login Screen (start destination)
    │   ├── → Dashboard (on login success)
    │   ├── → Create Account
    │   └── → Forgot Password
    │
    └── Dashboard Screen
        ├── → 8 Feature Screens
        ├── → Profile Screen
        └── 4 Tab Contents (Home, Invoices, Payments, Settings)
```

### 3. UI Component Hierarchy
```
Screens
    ├── LoginScreen
    │   ├── CustomTextField (Email)
    │   ├── CustomTextField (Password)
    │   └── GradientButton (Login)
    │
    └── DashboardScreen
        ├── TopAppBar
        ├── FeatureCard × 8
        └── BottomNavigationBar
```

### 4. Theme System
```
InvoiceGeneratorTheme (Theme.kt)
    ├── ColorScheme (Color.kt)
    │   ├── Primary Colors
    │   ├── Background Colors
    │   ├── Text Colors
    │   └── Status Colors
    │
    └── Typography (Type.kt)
        ├── Display Styles
        ├── Headline Styles
        ├── Title Styles
        ├── Body Styles
        └── Label Styles
```

## 📦 Dependencies Overview

### Core Dependencies
- `androidx.core:core-ktx` - Kotlin extensions
- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle management
- `androidx.activity:activity-compose` - Compose activity support

### Compose Dependencies
- `androidx.compose:compose-bom` - Bill of Materials
- `androidx.compose.ui:ui` - Core UI
- `androidx.compose.material3:material3` - Material Design 3
- `androidx.compose.material:material-icons-extended` - Extended icons

### Navigation
- `androidx.navigation:navigation-compose` - Navigation for Compose

### ViewModel
- `androidx.lifecycle:lifecycle-viewmodel-compose` - ViewModel support

## 🔄 Data Flow (Future Implementation)

```
UI Layer (Screens)
    ↕
ViewModel Layer (To be implemented)
    ↕
Repository Layer (To be implemented)
    ↕
Data Sources
    ├── Local (Room Database - To be implemented)
    └── Remote (API - To be implemented)
```

## 🎨 Resource Organization

### Strings (strings.xml)
- Login screen strings
- Dashboard screen strings
- Navigation labels
- Validation messages
- Common strings

### Colors (colors.xml + Color.kt)
- **XML**: For Android system use
- **Kotlin**: For Compose use
- Both contain the same color values

### Themes
- **XML (themes.xml)**: System theme (status bar color)
- **Kotlin (Theme.kt)**: Compose theme (Material 3)

## 📱 Screen Sizes Supported

- **Phone Portrait**: 360dp - 480dp
- **Phone Landscape**: 480dp - 720dp
- **Tablet Portrait**: 720dp - 1024dp
- **Tablet Landscape**: 1024dp+

## 🔐 Security Features

- Password visibility toggle
- Input validation
- ProGuard rules for release builds
- Secure backup rules

## 🚀 Build Variants

### Debug
- Debugging enabled
- No code obfuscation
- Faster build times

### Release
- ProGuard enabled
- Code obfuscation
- Optimized APK size

## 📈 Future Expansion Areas

### Planned Directories
```
app/src/main/java/com/invoicegenerator/
├── data/
│   ├── local/          # Room database
│   ├── remote/         # API services
│   ├── models/         # Data models
│   └── repository/     # Repository pattern
│
├── domain/
│   ├── models/         # Domain models
│   └── usecases/       # Business logic
│
└── viewmodels/         # ViewModels for screens
```

### Planned Features
- Invoice creation and management
- E-Way Bill generation
- Quotation management
- Payment tracking
- Customer management
- Product catalog
- Reports and analytics
- Cloud sync
- PDF generation
- Multi-language support

## 🛠️ Development Tools

### Required
- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or higher
- Android SDK API 34
- Gradle 8.2

### Recommended
- Android Emulator with API 34
- Git for version control
- Kotlin plugin (bundled with Android Studio)

## 📊 Code Metrics

- **Total Lines of Code**: ~1,500 lines
- **Kotlin Files**: 11
- **XML Files**: 6
- **Composable Functions**: ~25
- **Reusable Components**: 3
- **Screens**: 2 (+ 11 placeholder screens)
- **Navigation Routes**: 13

---

**Last Updated**: 2024  
**Version**: 1.0  
**Minimum SDK**: 24 (Android 7.0)  
**Target SDK**: 34 (Android 14)

